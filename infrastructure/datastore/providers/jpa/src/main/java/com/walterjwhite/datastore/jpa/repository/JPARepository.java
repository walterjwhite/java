package com.walterjwhite.datastore.jpa.repository;

import com.walterjwhite.datastore.api.model.Entity;
import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transaction;
import java.io.Serializable;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JPARepository implements Repository<EntityManager> {
  protected final EntityManager delegate;

  @Override
  public <EntityType extends Entity> EntityType create(EntityType entity) {
    delegate.persist(entity);
    return entity;
  }

  @Override
  public <EntityType extends Entity> EntityType update(EntityType entity) {
    return delegate.merge(entity);
  }

  @Override
  public void refresh(Entity entity) {
    delegate.refresh(entity);
  }

  @Override
  public void delete(Entity entity) {
    delegate.remove(entity);
  }

  @Override
  public Transaction getTransaction() {
    return new JPATransaction(delegate.getTransaction());
  }

  @Override
  public <EntityType extends Entity, IdType extends Serializable> EntityType findById(
      Class<EntityType> entityTypeClass, IdType id) {
    return delegate.find(entityTypeClass, id);
  }

  @Override
  public <EntityType extends Entity> Stream<EntityType> findAll(Class<EntityType> entityTypeClass) {
    throw new UnsupportedOperationException("findAll not supported");
  }
}
