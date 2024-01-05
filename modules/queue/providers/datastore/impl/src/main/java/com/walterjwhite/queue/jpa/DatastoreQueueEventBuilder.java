package com.walterjwhite.queue.jpa;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.queue.event.enumeration.EventActionType;
import com.walterjwhite.queue.event.model.QueuedEvent;
import com.walterjwhite.queue.event.service.QueueEventBuilder;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class DatastoreQueueEventBuilder implements QueueEventBuilder {
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public DatastoreQueueEventBuilder(Provider<Repository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public QueuedEvent build(AbstractEntity entity, Class jobSubscriberClass) {
    final Repository repository = repositoryProvider.get();

    final EventActionType eventActionType = EventActionType.Create;
    QueuedEvent queuedJob = null;
    if (true) {
      throw new UnsupportedOperationException("");
    }

    //        new QueuedEvent(
    //            jobSubscriberClass,
    //            repository.query(
    //                new FindEntityReferenceByTypeAndIdQuery(
    //                    repository.query(
    //                        new FindEntityTypeByNameQuery(entity.getClass().getSimpleName())),
    //                    entity.getId())),
    //            eventActionType);
    return repositoryProvider.get().create(queuedJob);
  }
}
