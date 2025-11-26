package com.walterjwhite.datastore.jdo.repository;

import com.walterjwhite.datastore.api.model.Entity;
import com.walterjwhite.datastore.api.repository.Repository;
import jakarta.inject.Inject;
import jakarta.transaction.Transaction;
import java.io.Serializable;
import java.util.stream.Stream;
import javax.jdo.JDOQLTypedQuery;
import javax.jdo.PersistenceManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JDORepository implements Repository<PersistenceManager> {
  protected final PersistenceManager delegate;

  @Override
  public <EntityType extends Entity> EntityType create(EntityType entity) {
    return delegate.makePersistent(entity);
  }

  @Override
  public <EntityType extends Entity> EntityType update(EntityType entity) {
    return delegate.makePersistent(entity);
  }

  @Override
  public void refresh(Entity entity) {
    delegate.refresh(entity);
  }

  @Override
  public void delete(Entity entity) {
    delegate.deletePersistent(entity);
  }

  @Override
  public Transaction getTransaction() {
    return new JDOTransaction(delegate.currentTransaction());
  }

  @Override
  public <EntityType extends Entity, IdType extends Serializable> EntityType findById(
      Class<EntityType> entityTypeClass, IdType id) {
    return delegate.getObjectById(entityTypeClass, id);
  }

  @Override
  public <EntityType extends Entity> Stream<EntityType> findAll(Class<EntityType> entityTypeClass) {
    return delegate.getManagedObjects(entityTypeClass).stream();
  }

  public <EntityType extends Entity> JDOQLTypedQuery<EntityType> getTypedQuery(
      final Class<EntityType> entityTypeClass) {
    return delegate.newJDOQLTypedQuery(entityTypeClass);
  }
}
