package com.walterjwhite.queue.guice;

public class FindEntityJobExecutorByEntityTypeQueryBuilder {
  @Transactional
  @Transactional(ignore = {NoResultException.class})
  public EntityJobExecutor findByResourceType(EntityType entityType) {
    final CriteriaQueryConfiguration<EntityJobExecutor> jobCriteriaQueryConfiguration =
        getCriteriaQuery();
    Predicate condition =
        criteriaBuilder.equal(
            jobCriteriaQueryConfiguration.getRoot().get(EntityJobExecutor_.entityType), entityType);
    jobCriteriaQueryConfiguration.getCriteriaQuery().where(condition);
    return entityManager
        .createQuery(jobCriteriaQueryConfiguration.getCriteriaQuery())
        .getSingleResult();
  }
}
