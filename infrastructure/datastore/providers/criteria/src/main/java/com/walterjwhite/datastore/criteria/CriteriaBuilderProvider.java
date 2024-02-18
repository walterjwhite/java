package com.walterjwhite.datastore.criteria;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.persistence.criteria.CriteriaBuilder;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CriteriaBuilderProvider implements Provider<CriteriaBuilder> {
    protected final EntityManager entityManager;

    public CriteriaBuilder get() {
        return entityManager.getCriteriaBuilder();
    }
}
