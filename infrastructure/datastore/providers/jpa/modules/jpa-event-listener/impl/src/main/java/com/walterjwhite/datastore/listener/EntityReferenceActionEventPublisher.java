package com.walterjwhite.datastore.listener;

import com.walterjwhite.datastore.api.event.EntityReferenceAction;
import com.walterjwhite.datastore.api.event.Transaction;
import com.walterjwhite.datastore.api.event.enumeration.JPAActionType;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.datastore.query.entityReference.FindEntityReferenceByTypeAndIdQueryConfiguration;
import com.walterjwhite.datastore.query.entityType.FindEntityTypeByNameQueryConfiguration;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EntityReferenceActionEventPublisher {
  // protected final EntityManager entityManager;
  protected final Provider<Repository> repositoryProvider;

  protected final ThreadLocal<Transaction> currentTransaction = new ThreadLocal<>();

  @PostPersist
  public void onPostPersist(Object o) {
    publish((AbstractEntity) o, JPAActionType.Persist);
  }

  @PostUpdate
  public void onPostUpdate(Object o) {
    publish((AbstractEntity) o, JPAActionType.Update);
  }

  @PostRemove
  public void onPostRemove(Object o) {
    publish((AbstractEntity) o, JPAActionType.Remove);
  }

  protected void publish(AbstractEntity entity, JPAActionType jpaActionType) {
    if (!isProcess(entity)) return;

    final Repository repository = repositoryProvider.get();

    // we want to only handle the POST events here, as this will be executed prior to the PrePersist
    // method on the AbstractEntity base class
    EntityReference entityReference = getEntityReference(repository, entity);
    repository.persist(
        new EntityReferenceAction(
            entityReference.getEntityType(),
            entityReference.getEntityId(),
            jpaActionType,
            getTransaction(repository)));
  }

  /**
   * When we create this entity, we don't want to respond to the event we just created, ignore it.
   * At the same token, other downstream listeners may be interested, so we would like to avoid
   * excluding default listeners here ...
   *
   * @param entity
   * @return
   */
  protected boolean isProcess(AbstractEntity entity) {
    return !EntityReferenceAction.class.isAssignableFrom(entity.getClass());
  }

  protected Transaction getTransaction(Repository repository) {
    if (currentTransaction.get() == null) {
      // current executing transaction ...
      // UUID even if the underlying TX is the same, instead, get that reference
      currentTransaction.set(new Transaction(repository.getEntityTransaction()));
    }

    return currentTransaction.get();
  }

  protected EntityReference getEntityReference(Repository repository, AbstractEntity entity) {
    if (EntityReference.class.isAssignableFrom(entity.getClass()))
      throw new IllegalStateException("Processing self:" + entity);

    return repository.query(
        new FindEntityReferenceByTypeAndIdQueryConfiguration(
            repository.query(
                new FindEntityTypeByNameQueryConfiguration(entity.getClass().getSimpleName())),
            entity.getId()));
  }
}
