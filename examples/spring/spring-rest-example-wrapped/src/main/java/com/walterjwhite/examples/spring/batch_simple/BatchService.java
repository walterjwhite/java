package com.walterjwhite.examples.spring.batch_simple;

import com.walterjwhite.examples.spring.mock.CSVWriter;
import com.walterjwhite.examples.spring.mock.Transaction;
import com.walterjwhite.examples.spring.mock.TransactionGenerator;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BatchService {
  private final Map<String, Future<?>> running = new ConcurrentHashMap<>();

  private volatile String lastExportFilename;

  public synchronized String getLastExportFileName() {
    return lastExportFilename;
  }

  private final ExecutorService workers =
      Executors.newFixedThreadPool(
          4,
          r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("batch-worker-" + t.getId());
            return t;
          });

  private final TransactionGenerator transactionGenerator;
  private final BatchJobRepository batchJobRepository;

  public synchronized void start(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return;
    }

    if (j.getStatus() == JobStatus.RUNNING) {
      LOGGER.info("Start requested but job {} is already running", id);
      return;
    }

    markUpstreamWanted(id);

    markDownstreamWanted(id);

    scheduleReadyJobs();
  }

  public synchronized void startOrRerun(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return;
    }
    if (j.getStatus() == JobStatus.COMPLETED) {
      markDownstreamRerun(id);
      scheduleReadyJobs();
      return;
    }
    start(id);
  }

  public synchronized boolean cancel(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return false;
    }
    j.setWantToRun(false);
    JobStatus s = j.getStatus();
    if (s == JobStatus.QUEUED) {
      j.setStatus(JobStatus.CANCELLED);
      j.setCompletedAt(Instant.now());
      LOGGER.info("Cancelled queued job {}", id);
      return true;
    }
    Future<?> f = running.get(id);
    if (f != null) {
      boolean cancelled = f.cancel(true);
      if (cancelled) {
        j.setStatus(JobStatus.CANCELLED);
        j.setCompletedAt(Instant.now());
        LOGGER.info("Requested cancel of running job {} -> {}", id, cancelled);
      } else {
        LOGGER.info("Attempted cancel of running job {} returned false", id);
      }
      return cancelled;
    }
    return false;
  }

  private void markUpstreamWanted(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return;
    }
    if (j.getStatus() == JobStatus.COMPLETED) {
      return;
    }

    for (String dep : j.getDependencies()) {
      markUpstreamWanted(dep);
    }

    if (!j.isWantToRun()) {
      j.setWantToRun(true);
      j.setSubmittedAt(Instant.now());
      if (j.getStatus() == JobStatus.PENDING
          || j.getStatus() == JobStatus.CANCELLED
          || j.getStatus() == JobStatus.FAILED) {
        j.setStatus(JobStatus.PENDING);
      }
      LOGGER.info("Marked job {} wantToRun=true", id);
    }
  }

  private void markDownstreamWanted(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return;
    }

    for (String depId : j.getDependents()) {
      BatchJob d = batchJobRepository.getJob(depId);
      if (d == null) {
        continue;
      }

      if (d.getStatus() == JobStatus.COMPLETED) {
        continue;
      }

      if (!d.isWantToRun()) {
        d.setWantToRun(true);
        d.setSubmittedAt(Instant.now());
        if (d.getStatus() == JobStatus.CANCELLED || d.getStatus() == JobStatus.FAILED) {
          d.setStatus(JobStatus.PENDING);
        }
        LOGGER.info("Marked downstream job {} wantToRun=true", depId);
      }

      markDownstreamWanted(depId);
    }
  }

  private void markDownstreamRerun(String id) {
    BatchJob j = batchJobRepository.getJob(id);
    if (j == null) {
      return;
    }

    j.setWantToRun(true);
    j.setSubmittedAt(Instant.now());
    j.setStatus(JobStatus.PENDING);
    j.setFailureReason(null);
    j.setStartedAt(null);
    j.setCompletedAt(null);
    for (String dep : j.getDependents()) {
      markDownstreamRerun(dep);
    }
    LOGGER.info("Marked job {} for rerun (and downstream)", id);
  }

  private synchronized void scheduleReadyJobs() {
    for (BatchJob job : batchJobRepository.listJobs()) {
      if (!job.isWantToRun()) {
        continue;
      }
      JobStatus s = job.getStatus();
      if (s == JobStatus.PENDING) {
        boolean depsOk = true;
        for (String did : job.getDependencies()) {
          BatchJob d = batchJobRepository.getJob(did);
          if (d == null || d.getStatus() != JobStatus.COMPLETED) {
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
    job.setStatus(JobStatus.QUEUED);
    job.setSubmittedAt(Instant.now());
    LOGGER.info("Submitting job {} to worker pool", job.getId());
    Future<?> f = workers.submit(() -> runJob(job));
    running.put(job.getId(), f);
  }

  private void runJob(BatchJob job) {
    synchronized (this) {
      job.setStatus(JobStatus.RUNNING);
      job.setStartedAt(Instant.now());
    }
    LOGGER.info("Started job {} ({}s)", job.getId(), job.getDurationSeconds());
    try {
      int seconds = Math.max(0, job.getDurationSeconds());
      for (int i = 0; i < seconds; i++) {
        Thread.sleep(1000);
        if (Thread.currentThread().isInterrupted()) {
          throw new InterruptedException("interrupted");
        }
      }
      synchronized (this) {
        job.setStatus(JobStatus.COMPLETED);
        job.setCompletedAt(Instant.now());
        job.setWantToRun(false);
      }
      LOGGER.info("Completed job {}", job.getId());

      if ("5".equals(job.getId())) {
        runExport(job);
      }

      scheduleReadyJobs();
    } catch (InterruptedException ie) {
      synchronized (this) {
        job.setStatus(JobStatus.CANCELLED);
        job.setCompletedAt(Instant.now());
        job.setWantToRun(false);
      }
      LOGGER.info("Job {} was cancelled", job.getId());
    } catch (Throwable t) {
      synchronized (this) {
        job.setStatus(JobStatus.FAILED);
        job.setFailureReason(t.getMessage());
        job.setCompletedAt(Instant.now());
        job.setWantToRun(false);
      }
      LOGGER.info("Job {} failed", job.getId());
    } finally {
      running.remove(job.getId());
    }
  }

  protected void runExport(final BatchJob job) {

    try {
      List<Transaction> transactions = transactionGenerator.generate();
      lastExportFilename = CSVWriter.toCSV(transactions);
      LOGGER.info("Exported transactions to CSV file: {}", lastExportFilename);
    } catch (Exception e) {
      synchronized (this) {
        job.setStatus(JobStatus.FAILED);
        job.setFailureReason(e.getMessage());
        job.setCompletedAt(Instant.now());
        job.setWantToRun(false);
      }
    }
  }

  public void shutdown() {
    workers.shutdownNow();
  }
}
