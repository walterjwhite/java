package com.walterjwhite.examples.spring.batch_simple;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

@Service
public class BatchService {

    private static final Logger log = LoggerFactory.getLogger(BatchService.class);

    private final Map<String, BatchJob> jobs = new LinkedHashMap<>();
    private final Map<String, Future<?>> running = new ConcurrentHashMap<>();
    private final ExecutorService workers = Executors.newFixedThreadPool(4, r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        t.setName("batch-worker-" + t.getId());
        return t;
    });

    @PostConstruct
    public void init() {
        jobs.put("1", new BatchJob("1", "Fetch Data", 4, Set.of()));
        jobs.put("2", new BatchJob("2", "Load", 5, Set.of("1")));
        jobs.put("3", new BatchJob("3", "Validate Accounts", 6, Set.of("2")));
        jobs.put("4", new BatchJob("4", "Validate Transactions", 3, Set.of("2")));
        jobs.put("5", new BatchJob("5", "Export", 5, Set.of("3", "4")));

        for (BatchJob job : jobs.values()) {
            for (String dep : job.getDependencies()) {
                BatchJob parent = jobs.get(dep);
                if (parent != null) parent.addDependent(job.getId());
            }
        }

        log.info("BatchService initialized with jobs: {}", jobs.keySet());
    }

    public synchronized List<BatchJob> listJobs() {
        return new ArrayList<>(jobs.values());
    }

    public synchronized BatchJob getJob(String id) {
        return jobs.get(id);
    }

    public synchronized void start(String id) {
        BatchJob j = jobs.get(id);
        if (j == null) return;
        markUpstreamWanted(id);
        scheduleReadyJobs();
    }

    public synchronized boolean cancel(String id) {
        BatchJob j = jobs.get(id);
        if (j == null) return false;
        j.setWantToRun(false);
        BatchJob.Status s = j.getStatus();
        if (s == BatchJob.Status.QUEUED) {
            j.setStatus(BatchJob.Status.CANCELLED);
            j.setCompletedAt(Instant.now());
            log.info("Cancelled queued job {}", id);
            return true;
        }
        Future<?> f = running.get(id);
        if (f != null) {
            boolean cancelled = f.cancel(true);
            if (cancelled) {
                j.setStatus(BatchJob.Status.CANCELLED);
                j.setCompletedAt(Instant.now());
                log.info("Requested cancel of running job {} -> {}", id, cancelled);
            } else {
                log.info("Attempted cancel of running job {} returned false", id);
            }
            return cancelled;
        }
        return false;
    }

    public synchronized void rerun(String id) {
        BatchJob j = jobs.get(id);
        if (j == null) return;
        markDownstreamRerun(id);
        scheduleReadyJobs();
    }

    private void markUpstreamWanted(String id) {
        BatchJob j = jobs.get(id);
        if (j == null) return;
        for (String dep : j.getDependencies()) {
            markUpstreamWanted(dep);
        }
        if (!j.isWantToRun()) {
            j.setWantToRun(true);
            j.setSubmittedAt(Instant.now());
            if (j.getStatus() == BatchJob.Status.PENDING || j.getStatus() == BatchJob.Status.CANCELLED || j.getStatus() == BatchJob.Status.FAILED || j.getStatus() == BatchJob.Status.COMPLETED) {
                j.setStatus(BatchJob.Status.PENDING);
            }
            log.info("Marked job {} wantToRun=true", id);
        }
    }

    private void markDownstreamRerun(String id) {
        BatchJob j = jobs.get(id);
        if (j == null) return;
        j.setWantToRun(true);
        j.setSubmittedAt(Instant.now());
        j.setStatus(BatchJob.Status.PENDING);
        j.setFailureReason(null);
        j.setStartedAt(null);
        j.setCompletedAt(null);
        for (String dep : j.getDependents()) {
            markDownstreamRerun(dep);
        }
        log.info("Marked job {} for rerun (and downstream)", id);
    }

    private synchronized void scheduleReadyJobs() {
        for (BatchJob job : jobs.values()) {
            if (!job.isWantToRun()) continue;
            BatchJob.Status s = job.getStatus();
            if (s == BatchJob.Status.PENDING) {
                boolean depsOk = true;
                for (String did : job.getDependencies()) {
                    BatchJob d = jobs.get(did);
                    if (d == null || d.getStatus() != BatchJob.Status.COMPLETED) {
                        depsOk = false;
                        break;
                    }
                }
                if (depsOk) {
                    submitJob(job);
                }
            }
        }
    }

    private void submitJob(BatchJob job) {
        job.setStatus(BatchJob.Status.QUEUED);
        job.setSubmittedAt(Instant.now());
        log.info("Submitting job {} to worker pool", job.getId());
        Future<?> f = workers.submit(() -> runJob(job));
        running.put(job.getId(), f);
    }

    private void runJob(BatchJob job) {
        synchronized (this) {
            job.setStatus(BatchJob.Status.RUNNING);
            job.setStartedAt(Instant.now());
        }
        log.info("Started job {} ({}s)", job.getId(), job.getDurationSeconds());
        try {
            int seconds = Math.max(0, job.getDurationSeconds());
            for (int i = 0; i < seconds; i++) {
                Thread.sleep(1000);
                if (Thread.currentThread().isInterrupted()) throw new InterruptedException("interrupted");
            }
            synchronized (this) {
                job.setStatus(BatchJob.Status.COMPLETED);
                job.setCompletedAt(Instant.now());
                job.setWantToRun(false);
            }
            log.info("Completed job {}", job.getId());
            scheduleReadyJobs();
        } catch (InterruptedException ie) {
            synchronized (this) {
                job.setStatus(BatchJob.Status.CANCELLED);
                job.setCompletedAt(Instant.now());
                job.setWantToRun(false);
            }
            log.info("Job {} was cancelled", job.getId());
        } catch (Throwable t) {
            synchronized (this) {
                job.setStatus(BatchJob.Status.FAILED);
                job.setFailureReason(t.getMessage());
                job.setCompletedAt(Instant.now());
                job.setWantToRun(false);
            }
        } finally {
            running.remove(job.getId());
        }
    }

    public void shutdown() {
        workers.shutdownNow();
    }
}