package com.walterjwhite.examples.spring.async;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
public class AsyncTaskService implements DisposableBean {

    private static final Logger log = LoggerFactory.getLogger(AsyncTaskService.class);

    private final BlockingQueue<TaskInfo> queue = new LinkedBlockingQueue<>();
    private final ConcurrentMap<String, TaskInfo> allTasks = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, Future<?>> running = new ConcurrentHashMap<>();

    private final ExecutorService executor;
    private final Thread dispatcherThread;
    private volatile boolean runningDispatcher = true;

    public AsyncTaskService() {
        this.executor = Executors.newFixedThreadPool(4, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("async-worker-" + t.getId());
            return t;
        });
        this.dispatcherThread = new Thread(this::dispatchLoop, "async-dispatcher");
        this.dispatcherThread.setDaemon(true);
    }

    @PostConstruct
    public void start() {
        log.info("Starting async dispatcher thread");
        dispatcherThread.start();
    }

    private void dispatchLoop() {
        while (runningDispatcher && !Thread.currentThread().isInterrupted()) {
            try {
                TaskInfo task = queue.take();
                if (task.getStatus() == TaskInfo.Status.CANCELLED) {
                    log.info("Skipping cancelled queued task {}", task.getId());
                    continue;
                }
                task.setStatus(TaskInfo.Status.RUNNING);
                task.setStartedAt(Instant.now());
                Future<?> f = executor.submit(() -> runTask(task));
                running.put(task.getId(), f);
                log.info("Dispatched task {} to worker", task.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception ex) {
                log.error("Dispatcher loop error", ex);
            }
        }
    }

    private void runTask(TaskInfo task) {
        try {
            log.info("Running task {} ({}s)", task.getId(), task.getDurationSeconds());
            int total = Math.max(0, task.getDurationSeconds());
            for (int i = 0; i < total; i++) {
                if (Thread.currentThread().isInterrupted() || task.getStatus() == TaskInfo.Status.CANCELLED) {
                    throw new InterruptedException("Task interrupted or cancelled");
                }
                Thread.sleep(1000);
            }
            if (Thread.currentThread().isInterrupted() || task.getStatus() == TaskInfo.Status.CANCELLED) {
                throw new InterruptedException("Task interrupted or cancelled");
            }

            task.setStatus(TaskInfo.Status.COMPLETED);
            task.setCompletedAt(Instant.now());
            log.info("Completed task {}", task.getId());
        } catch (InterruptedException ie) {
            task.setStatus(TaskInfo.Status.CANCELLED);
            task.setCompletedAt(Instant.now());
            log.info("Task {} was cancelled/interrupted", task.getId());
            Thread.currentThread().interrupt();
        } catch (Throwable t) {
            task.setStatus(TaskInfo.Status.FAILED);
            task.setFailureReason(t.getMessage());
            task.setCompletedAt(Instant.now());
            log.error("Task {} failed", task.getId(), t);
        } finally {
            running.remove(task.getId());
        }
    }

    public String submit(String name, int durationSeconds) {
        String id = UUID.randomUUID().toString();
        TaskInfo task = new TaskInfo(id, name == null ? "task-" + id.substring(0, 6) : name, durationSeconds);
        allTasks.put(id, task);
        boolean added = queue.add(task);
        log.info("Submitted task id={} name={} duration={} addedToQueue={}", id, name, durationSeconds, added);
        return id;
    }

    public List<TaskInfo> listQueued() {
        return new ArrayList<>(queue);
    }

    public List<TaskInfo> listRunning() {
        return running.keySet().stream()
                .map(allTasks::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<TaskInfo> listAll() {
        return new ArrayList<>(allTasks.values());
    }

    public TaskInfo get(String id) {
        return allTasks.get(id);
    }

    public TaskInfo cancel(String id) {
        TaskInfo task = allTasks.get(id);
        if (task == null) return null;

        if (task.getStatus() == TaskInfo.Status.QUEUED) {
            boolean removed = queue.remove(task);
            if (removed) {
                task.setStatus(TaskInfo.Status.CANCELLED);
                task.setCompletedAt(Instant.now());
                log.info("Cancelled queued task {}", id);
                return task;
            }
        }

        Future<?> f = running.get(id);
        if (f != null) {
            log.info("Attempting cancel of running task {}", id);
            boolean cancelled = f.cancel(true); // interrupt worker thread
            if (cancelled) {
                task.setStatus(TaskInfo.Status.CANCELLED);
                task.setCompletedAt(Instant.now());
                log.info("Cancellation requested for running task {}", id);
            } else {
                log.info("Cancellation request for {} returned false (maybe already completed)", id);
            }
            return task;
        }

        log.info("Cancel called for task {} but status is {}", id, task.getStatus());
        return task;
    }

    @Override
    public void destroy() {
        runningDispatcher = false;
        dispatcherThread.interrupt();
        executor.shutdownNow();
    }
}