package com.walterjwhite.queue.api.service;

import com.walterjwhite.queue.api.model.AbstractQueued;
import com.walterjwhite.queue.api.model.JobExecution;
import java.util.List;

public interface QueueService {
  <QueuedType extends AbstractQueued> QueuedType cancel(QueuedType queued);

  JobExecution cancel(JobExecution jobExecution);

  <QueuedType extends AbstractQueued> QueuedType queue(QueuedType queued);

  boolean wasCancelled(AbstractQueued queued);

  <QueuedType extends AbstractQueued> List<QueuedType> findAssignable();

  <QueuedType extends AbstractQueued> List<QueuedType> findRecurringAssignable();

  <QueuedType extends AbstractQueued> List<QueuedType> findAbortedJobExecutions();

  <QueuedType extends AbstractQueued> QueuedType update(QueuedType queued);
}
