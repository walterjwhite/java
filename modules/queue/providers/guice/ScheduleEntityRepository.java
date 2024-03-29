package com.walterjwhite.job.impl.repository;

import com.walterjwhite.datastore.api.repository.GenericEntityRepository;
import com.walterjwhite.datastore.modules.criteria.CriteriaQueryConfiguration;
import com.walterjwhite.job.api.enumeration.JobExecutionState;
import com.walterjwhite.job.api.model.JobExecution;
import com.walterjwhite.job.api.model.JobExecution_;
import com.walterjwhite.job.api.model.scheduling.AbstractSchedule;
import com.walterjwhite.job.api.model.scheduling.AbstractSchedule_;
import jakarta.inject.Inject;
import java.util.List;

public class ScheduleEntityRepository extends GenericEntityRepository<AbstractSchedule> {
  @Inject
  public ScheduleEntityRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, AbstractSchedule.class);
  }

  
  public List<AbstractSchedule> findPendingExecutions() {
    final CriteriaQueryConfiguration<AbstractSchedule> jobCriteriaQueryConfiguration =
        getCriteriaQuery();
    Predicate noJobExecutions =
        criteriaBuilder.isEmpty(
            jobCriteriaQueryConfiguration.getRoot().get(AbstractSchedule_.jobExecutions));

    Join<AbstractSchedule, JobExecution> jobExecution =
        jobCriteriaQueryConfiguration.getRoot().join(AbstractSchedule_.jobExecutions);
    Predicate pendingJobExecutions =
        criteriaBuilder.equal(
            jobExecution.get(JobExecution_.jobExecutionState), JobExecutionState.Pending);
    Predicate scheduledJobExecutions =
        criteriaBuilder.equal(
            jobExecution.get(JobExecution_.jobExecutionState), JobExecutionState.Scheduled);

    Predicate incompleteJobExecutions =
        criteriaBuilder.or(pendingJobExecutions, scheduledJobExecutions);

    jobCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(criteriaBuilder.or(noJobExecutions, incompleteJobExecutions));
    return (entityManager
        .createQuery(jobCriteriaQueryConfiguration.getCriteriaQuery())
        .getResultList());
  }

  public boolean hasExistingPendingOrScheduledJobExecution(AbstractSchedule schedule) {
    final CriteriaQueryConfiguration<AbstractSchedule> jobCriteriaQueryConfiguration =
        getCriteriaQuery();
    Predicate noJobExecutions =
        criteriaBuilder.isEmpty(
            jobCriteriaQueryConfiguration.getRoot().get(AbstractSchedule_.jobExecutions));

    Predicate scheduleCriteria =
        criteriaBuilder.equal(
            jobCriteriaQueryConfiguration.getRoot().get(AbstractSchedule_.id), schedule.getId());

    Join<AbstractSchedule, JobExecution> jobExecution =
        jobCriteriaQueryConfiguration.getRoot().join(AbstractSchedule_.jobExecutions);
    Predicate pendingJobExecutions =
        criteriaBuilder.equal(
            jobExecution.get(JobExecution_.jobExecutionState), JobExecutionState.Pending);
    Predicate scheduledJobExecutions =
        criteriaBuilder.equal(
            jobExecution.get(JobExecution_.jobExecutionState), JobExecutionState.Scheduled);

    Predicate incompleteJobExecutions =
        criteriaBuilder.or(pendingJobExecutions, scheduledJobExecutions);

    jobCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(
            criteriaBuilder.and(
                scheduleCriteria, criteriaBuilder.or(noJobExecutions, incompleteJobExecutions)));
    try {
      entityManager.createQuery(jobCriteriaQueryConfiguration.getCriteriaQuery()).getSingleResult();
      return true;
    } catch (NoResultException e) {
      return false;
    }
  }
}
