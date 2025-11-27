package com.walterjwhite.queue.api.service;

import com.walterjwhite.queue.api.job.RunningFuture;
import com.walterjwhite.queue.api.model.AbstractQueued;
import com.walterjwhite.queue.api.model.JobExecution;

public interface JobWorkerService {
  AbstractQueued queue(AbstractQueued queued);

  void cancel(AbstractQueued queued);

  void cancel(JobExecution jobExecution);

  void remove(RunningFuture runningFuture);
}
