package com.walterjwhite.datastore.jdo;

import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.datastore.api.repository.Repository;
import java.util.List;
import jakarta.inject.Inject;
import javax.jdo.PersistenceManager;
import javax.transaction.Transaction;
import lombok.Getter;

@Getter
public class JDORepository implements Repository {
  protected final PersistenceManager persistenceManager;
  // protected final XAResource xaResource;

  @Inject
  public JDORepository(PersistenceManager persistenceManager) {
    this.persistenceManager = persistenceManager;
  }

  @Override
  public <EntityType extends Entity> EntityType create(EntityType entity) {
    return persistenceManager.makePersistent(entity);
  }

  @Override
  public <EntityType extends Entity> EntityType update(EntityType entity) {
    return persistenceManager.makePersistent(entity);
  }

  @Override
  public void refresh(Entity entity) {
    persistenceManager.refresh(entity);
  }

  @Override
  public void delete(Entity entity) {
    persistenceManager.deletePersistent(entity);
  }

  @Override
  public Transaction getTransaction() {
    // return persistenceManager.currentTransaction();
    return null;
  }

  @Override
  public <EntityType extends Entity> EntityType findById(
      Class<EntityType> entityTypeClass, Integer id) {
    return persistenceManager.getObjectById(entityTypeClass, id);
  }

  @Override
  public <EntityType extends Entity> List<EntityType> findAll(Class<EntityType> entityTypeClass) {
    // return persistenceManager.;
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <EntityType extends Entity, ResultType> ResultType query(
      QueryConfiguration<EntityType, ResultType> query) {
    return null;
  }
}
