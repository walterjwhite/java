package com.walterjwhite.datastore.api.repository;

import com.walterjwhite.datastore.api.model.entity.Entity;
import java.util.List;
import javax.transaction.Transaction;

/**
 * Provides an agnostic API (criteria / querydsl / ES / SOLR / etc.) for querying data backed by
 * JPA.
 */
public interface Repository {
  <EntityType extends Entity> EntityType create(EntityType entity);

  <EntityType extends Entity> EntityType update(EntityType entity);

  void refresh(Entity entity);

  void delete(Entity entity);

  // PersistenceManager getPersistenceManager();

  Transaction getTransaction();

  <EntityType extends Entity> EntityType findById(
      final Class<EntityType> entityTypeClass, final Integer id);

  <EntityType extends Entity> List<EntityType> findAll(final Class<EntityType> entityTypeClass);

  <EntityType extends Entity, ResultType extends Object> ResultType query(
      QueryConfiguration<EntityType, ResultType> query);
}
