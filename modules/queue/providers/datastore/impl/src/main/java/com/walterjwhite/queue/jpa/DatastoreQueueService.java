package com.walterjwhite.queue.jpa;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.queue.api.enumeration.ExecutionState;
import com.walterjwhite.queue.api.model.AbstractQueued;
import com.walterjwhite.queue.api.model.JobExecution;
import com.walterjwhite.queue.api.model.Worker;
import com.walterjwhite.queue.api.service.JobWorkerService;
import com.walterjwhite.queue.impl.scheduling.AbstractQueueService;
import com.walterjwhite.queue.jpa.query.FindAbortedQueuedQuery;
import com.walterjwhite.queue.jpa.query.FindAssignableQueuedJobsQuery;
import com.walterjwhite.queue.jpa.query.FindRecurringAssignableQueuedJobsQuery;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.reflections.Reflections;

public class DatastoreQueueService extends AbstractQueueService {
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public DatastoreQueueService(
      /*@Secondary*/ Provider<Repository> repositoryProvider,
      Reflections reflections,
      JobWorkerService jobWorkerService,
      Worker worker) {
    super(jobWorkerService, worker);
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  protected <QueuedType extends AbstractQueued> QueuedType refresh(QueuedType queuedJob) {
    repositoryProvider.get().refresh(queuedJob);
    return queuedJob;
  }

  @Override
  protected JobExecution refresh(JobExecution jobExecution) {
    repositoryProvider.get().refresh(jobExecution);
    return jobExecution;
  }

  @Override
  public <QueuedType extends AbstractQueued> QueuedType update(QueuedType queuedJob) {
    return repositoryProvider.get().update(queuedJob);
  }

  @Override
  protected JobExecution update(JobExecution jobExecution) {
    return repositoryProvider.get().update(jobExecution);
  }

  @Override
  protected <QueuedType extends AbstractQueued> QueuedType create(QueuedType queuedJob) {
    // system jobs are internal and should not be recorded
    if (!queuedJob.isSystem()) return repositoryProvider.get().create(queuedJob);

    return queuedJob;
  }

  @Override
  public boolean wasCancelled(AbstractQueued queued) {
    AbstractQueued persistedEntity =
        repositoryProvider.get().findById(queued.getClass(), queued.getId());
    return ExecutionState.Cancelled.equals(
        persistedEntity.getCurrentJobExecution().getExecutionState());
  }

  @Override
  public <QueuedType extends AbstractQueued> List<QueuedType> findAssignable() {
    return repositoryProvider.get().query(new FindAssignableQueuedJobsQuery());
  }

  @Override
  public <QueuedType extends AbstractQueued> List<QueuedType> findRecurringAssignable() {
    return repositoryProvider.get().query(new FindRecurringAssignableQueuedJobsQuery());
  }

  @Override
  public <QueuedType extends AbstractQueued> List<QueuedType> findAbortedJobExecutions() {
    return repositoryProvider.get().query(new FindAbortedQueuedQuery());
  }

  //  protected void doRemove(QueuedJob queuedJob) {
  //    repositoryProvider.get().merge(queuedJob);
  //  }
  //
  //  protected void doQueue(QueuedJob queuedJob) {
  //    // if(queuedJob.getVersion() == null)
  //    // repositoryProvider.get().create(queuedJob);
  //  }
}
