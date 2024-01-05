package com.walterjwhite.infrastructure.datastore.jpa.event.listener.provider.guice;

import com.google.common.eventbus.AsyncEventBus;
import com.walterjwhite.datastore.api.event.JPAEvent;
import com.walterjwhite.datastore.api.event.service.JPAEventPublisher;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;

public class GoogleGuiceJPAEventPublisher implements JPAEventPublisher {
  protected final AsyncEventBus asyncEventBus =
      ApplicationHelper.getApplicationInstance().getInjector().getInstance(AsyncEventBus.class);

  @Override
  public void publish(JPAEvent jpaEvent) {
    asyncEventBus.post(jpaEvent);
  }
}
