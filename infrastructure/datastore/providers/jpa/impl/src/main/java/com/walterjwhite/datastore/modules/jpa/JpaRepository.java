package com.walterjwhite.datastore.modules.jpa;

import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.datastore.api.repository.QueryBuilder;
import com.walterjwhite.datastore.api.repository.QueryBuilderResolver;
import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.query.FindAllQuery;
import com.walterjwhite.datastore.util.EntityInstanceUtil;
import jakarta.persistence.NoResultException;
import java.io.Serializable;
import java.util.List;
import jakarta.inject.Inject;
import javax.transaction.Transaction;
import lombok.Getter;

/**
 * In order to easily and "conveniently" support multiple persistence units, this class is abstract
 * The "correct" EntityManager will be injected by extending this class and annotating the requested
 * entity manager
 */
@Getter
public class JpaRepository implements Repository {
  protected final EntityManager entityManager;
  protected final QueryBuilderResolver queryBuilderResolver;

  @Inject
  public JpaRepository(EntityManager entityManager, QueryBuilderResolver queryBuilderResolver) {

    this.entityManager = entityManager;
    this.queryBuilderResolver = queryBuilderResolver;
  }

  public <EntityType extends Entity> EntityType create(EntityType entity) {
    entityManager.persist(entity);
    return (entity);
  }

  public <EntityType extends Entity> EntityType update(EntityType entity) {
    return (entityManager.merge(entity));
  }

  public void refresh(Entity entity) {
    entityManager.refresh(entity);
  }

  public void delete(Entity entity) {
    entityManager.remove(entity);
  }

  public Serializable get(Class<? extends Entity> entityClass, final String id) {
    return entityManager.find(entityClass, id);
  }

  public Transaction getTransaction() {
    /*return entityManager.getTransaction();*/
    return null;
  }

  public <EntityType extends Entity> List<EntityType> findAll(Class<EntityType> entityTypeClass) {
    return (List<EntityType>) query(new FindAllQuery<>(0, -1, entityTypeClass));
  }

  public <EntityType extends Entity> EntityType findById(
      final Class<EntityType> entityTypeClass, final Integer id) {
    return entityManager.find(entityTypeClass, id);
  }

  public <EntityType extends Entity, ResultType extends Object> ResultType query(
      QueryConfiguration<EntityType, ResultType> query) {
    try {
      final QueryBuilder builder = queryBuilderResolver.getBuilder(query);
      return (ResultType) builder.get(entityManager, query);
    } catch (NoResultException e) {
      return handleAutoCreate(query, e);
    }
  }

  protected <EntityType extends Entity, ResultType extends Object> ResultType handleAutoCreate(
      QueryConfiguration<EntityType, ResultType> query, NoResultException e) {
    if (query.supportsAutoCreate()) {
      if (query.isConstruct()) {

        final Object[] createArguments = query.getAutoCreateArguments();
        if (createArguments.length == 1
            && createArguments[0].getClass().equals(query.getResultTypeClass()))
          return (ResultType) doCreate((EntityType) createArguments[0]);

        return (ResultType)
            doCreate(
                EntityInstanceUtil.instantiateNewObject(
                    query.getEntityTypeClass(), createArguments));
      }

      //      return (ResultType)
      //          create(query.getPersistenceOptions()[0].getTransientEntity());
    }

    throw e;
  }

  protected <EntityType extends Entity> EntityType doCreate(EntityType entity) {
    if (entity == null)
      throw new IllegalArgumentException(
          "Requested auto-create, but arguments are null, unable to instantiate, application is"
              + " mis-configured");

    // EntityTransaction entityTransaction = getEntityTransaction();
    // entityTransaction.begin();

    try {

      entityManager.persist(entity);
      // entityTransaction.commit();

      return entity;
    } catch (Exception e) {
      // entityTransaction.rollback();
      throw e;
    }
  }
}
