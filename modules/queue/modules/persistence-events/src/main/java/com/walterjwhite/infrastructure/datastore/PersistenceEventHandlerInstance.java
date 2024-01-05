package com.walterjwhite.infrastructure.datastore;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.queue.event.enumeration.EventActionType;
import com.walterjwhite.queue.event.service.EventService;
import java.lang.reflect.Method;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Before the create method completes, create any corresponding queue event jobs. */
@Getter
@RequiredArgsConstructor
public class PersistenceEventHandlerInstance {
  private static EventService EVENT_SERVICE;

  //  protected final ProceedingJoinPoint proceedingJoinPoint;
  protected final Method method;
  protected final Object[] arguments;

  public void call() {
    initializeEventService();

    EVENT_SERVICE.queue(getEntity(), getEventActionType());
  }

  protected <EntityType extends AbstractEntity> EntityType getEntity() {
    return (EntityType) /*proceedingJoinPoint.getArgs()*/ arguments[0];
  }

  protected EventActionType getEventActionType() {
    return null;
  }

  private static synchronized void initializeEventService() {
    if (EVENT_SERVICE == null)
      EVENT_SERVICE =
          ApplicationHelper.getApplicationInstance().getInjector().getInstance(EventService.class);
  }
}
