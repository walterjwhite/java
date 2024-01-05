package com.walterjwhite.datastore.modules.querydsl.repository;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.datastore.modules.jpa.JpaRepository;
import java.util.List;

public abstract class QuerydslRepository<EntityType extends AbstractEntity> extends JpaRepository {
  public QuerydslRepository(
      EntityManager entityManager, QueryBuilderResolver queryBuilderResolver) {
    super(entityManager, queryBuilderResolver);
  }

  //  @Override
  //  public List<? extends AbstractEntity> findAll(Class<? extends AbstractEntity> entityClass) {
  //    return null;
  //  }
  //
  //  @Override
  //  public List<? extends AbstractEntity> findAll(
  //      Class<? extends AbstractEntity> entityClass, int offset, int limit) {
  //    return null;
  //  }
  //
  //  @Override
  //  public int count(Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
  //    return 0;
  //  }
  //
  //  @Override
  //  public List<EntityType> get(
  //      Class<? extends AbstractEntity> entityClass,
  //      int offset,
  //      int recordCount,
  //      Predicate... conditions) {
  //    return null;
  //  }
  //
  //  @Override
  //  public Iterator<EntityType> iterate(
  //      Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
  //    return null;
  //  }
  //
  //  @Override
  //  public EntityType get(
  //      Class<? extends AbstractEntity> entityClass, int offset, Predicate... conditions) {
  //    return null;
  //  }
  //
  //  @Override
  //  public EntityType findRandom(
  //      Class<? extends AbstractEntity> entityClass, Predicate... conditions) {
  //    return null;
  //  }
  //
  //  @Override
  //  public EntityType findById(String id) {
  //    return null;
  //  }

  @Override
  public <EntityType extends AbstractEntity> List<EntityType> findAll(
      Class<EntityType> entityTypeClass) {
    return null;
  }

  @Override
  public <EntityType extends AbstractEntity, ResultType extends Object> ResultType query(
      QueryConfiguration<EntityType, ResultType> query) {
    return null;
  }
}
