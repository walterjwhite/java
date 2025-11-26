package com.walterjwhite.datastore.criteria;

import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CriteriaBuilderProvider implements Provider<CriteriaBuilder> {
  protected final EntityManager entityManager;

  public CriteriaBuilder get() {
    return entityManager.getCriteriaBuilder();
  }
}
