package com.walterjwhite.datastore.api.repository;

import com.walterjwhite.datastore.api.model.Entity;
import jakarta.transaction.Transaction;
import java.io.Serializable;
import java.util.stream.Stream;

public interface Repository<DelegateType> {
  DelegateType getDelegate();

  <EntityType extends Entity> EntityType create(EntityType entity);

  <EntityType extends Entity> EntityType update(EntityType entity);

  void refresh(Entity entity);

  void delete(Entity entity);


  Transaction getTransaction();

  <EntityType extends Entity, IdType extends Serializable> EntityType findById(
      final Class<EntityType> entityTypeClass, final IdType id);

  <EntityType extends Entity> Stream<EntityType> findAll(final Class<EntityType> entityTypeClass);
}
