package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.criteria.AbstractCriteriaQueryService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;

public class QueryByNameEqualsJPAEntity extends AbstractCriteriaQueryService<JPAEntity> {
  @Inject
  public QueryByNameEqualsJPAEntity(EntityManager entityManager, CriteriaBuilder criteriaBuilder) {
    super(entityManager, criteriaBuilder, JPAEntity.class);
  }

  public JPAEntity get(final String name) {
    criteriaQuery.where(
        criteriaBuilder.equal(root.get(com.walterjwhite.examples.cli.JPAEntity_.name), name));
    return getSingleResult();
  }
}
