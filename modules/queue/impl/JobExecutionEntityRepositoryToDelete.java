package com.walterjwhite.job.impl.repository;

import com.walterjwhite.datastore.api.repository.GenericEntityRepository;
import com.walterjwhite.job.api.enumeration.JobExecutionState;
import com.walterjwhite.job.api.model.JobExecution;
import java.time.LocalDateTime;
import jakarta.inject.Inject;
import javax.transaction.Transactional;

public class JobExecutionEntityRepositoryToDelete extends GenericEntityRepository<JobExecution> {
  @Inject
  public JobExecutionEntityRepositoryToDelete(
      EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, JobExecution.class);
  }

  //  @Transactional
  //  public JobExecution fetchJobExecutionInstance(final Integer jobExecutionId) {
  //    JobExecution jobExecution = entityManager.find(JobExecution.class, jobExecutionId);
  //
  //    if (JobExecutionState.Pending.equals(jobExecution.getExecutionState())) {
  //      jobExecution.setJobExecutionState(JobExecutionState.Scheduled);
  //      entityManager.merge(jobExecution);
  //    }
  //
  //    return jobExecution;
  //  }

  @Transactional
  public JobExecution updateJobExecution(JobExecution jobExecution, Throwable throwable) {
    jobExecution = entityManager.find(JobExecution.class, jobExecution.getId());
    entityManager.refresh(jobExecution);

    jobExecution.setEndDateTime(LocalDateTime.now());
    if (throwable != null) {
      jobExecution.setJobExecutionState(JobExecutionState.Exception);
      jobExecution.setThrowable(throwable);

      //
      // jobExecution.getSchedule().setRemainingRetries(jobExecution.getSchedule().getRemainingRetries() - 1);
    } else {
      jobExecution.setJobExecutionState(JobExecutionState.Completed);
    }

    return merge(jobExecution);
  }
}
