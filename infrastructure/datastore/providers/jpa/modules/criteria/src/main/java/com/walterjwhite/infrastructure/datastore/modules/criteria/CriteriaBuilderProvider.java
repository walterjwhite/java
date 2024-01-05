package com.walterjwhite.infrastructure.datastore.modules.criteria;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

/** Provides an instance of CriteriaBuilder so that we may build a query easily. */
public class CriteriaBuilderProvider implements Provider<CriteriaBuilder> {
  protected final EntityManager entityManager;

  @Inject
  public CriteriaBuilderProvider(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public CriteriaBuilder get() {
    return (entityManager.getCriteriaBuilder());
  }
}
