package com.walterjwhite.infrastructure.datastore.primary;

import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import jakarta.inject.Inject;

public class SecondaryJpaRepository extends JpaRepository {
  @Inject
  public SecondaryJpaRepository(
      @Secondary EntityManager entityManager, QueryBuilderResolver queryBuilderResolver) {
    super(entityManager, queryBuilderResolver);
  }
}
