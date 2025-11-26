package com.walterjwhite.job.impl.repository;

import com.walterjwhite.datastore.api.repository.GenericEntityRepository;
import com.walterjwhite.datastore.modules.criteria.CriteriaQueryConfiguration;
import jakarta.inject.Inject;

public class JobEntityRepository extends GenericEntityRepository<Job> {
  @Inject
  public JobEntityRepository(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, Job.class);
  }


  public Job findJobByExecutorAndResource(
      JobExecutor jobExecutor, EntityReference entityReference) {
    final CriteriaQueryConfiguration<Job> jobCriteriaQueryConfiguration = getCriteriaQuery();
    Predicate jobExecutorCondition =
        criteriaBuilder.equal(
            jobCriteriaQueryConfiguration.getRoot().get(Job_.jobExecutor), jobExecutor);
    Predicate entityCondition =
        criteriaBuilder.equal(
            jobCriteriaQueryConfiguration.getRoot().get(Job_.entityReference), entityReference);

    jobCriteriaQueryConfiguration
        .getCriteriaQuery()
        .where(criteriaBuilder.and(jobExecutorCondition, entityCondition));
    return (entityManager
        .createQuery(jobCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult());
  }
}
