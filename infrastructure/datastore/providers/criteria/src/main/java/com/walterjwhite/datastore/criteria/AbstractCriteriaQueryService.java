package com.walterjwhite.datastore.criteria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.stream.Stream;

public abstract class AbstractCriteriaQueryService<DataType> {
  protected final EntityManager entityManager;
  protected final CriteriaBuilder criteriaBuilder;

  protected final CriteriaQuery<DataType> criteriaQuery;
  protected final Root<DataType> root;

  public AbstractCriteriaQueryService(
      final EntityManager entityManager,
      final CriteriaBuilder criteriaBuilder,
      final Class<DataType> dataTypeClass) {
    this.entityManager = entityManager;
    this.criteriaBuilder = criteriaBuilder;

    criteriaQuery = criteriaBuilder.createQuery(dataTypeClass);
    root = criteriaQuery.from(dataTypeClass);
    criteriaQuery.select(root);
  }

  protected Stream<DataType> getResultStream() {
    final TypedQuery<DataType> typedQuery = entityManager.createQuery(criteriaQuery);
    return typedQuery.getResultStream();
  }

  protected DataType getSingleResult() {
    final TypedQuery<DataType> typedQuery = entityManager.createQuery(criteriaQuery);
    return typedQuery.getSingleResult();
  }
}
