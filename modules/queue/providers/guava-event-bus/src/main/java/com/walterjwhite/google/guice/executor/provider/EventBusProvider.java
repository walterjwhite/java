package com.walterjwhite.google.guice.executor.provider;

import com.google.common.eventbus.EventBus;
import com.walterjwhite.google.guice.annotation.EventBusOnly;
import com.walterjwhite.job.impl.property.property.EventBusName;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.concurrent.ExecutorService;

public class EventBusProvider implements Provider<EventBus> {
  protected final EventBus eventBus;


  @Inject
  public EventBusProvider(
      @EventBusOnly ExecutorService executorService,
      @Property(EventBusName.class) String eventBusName) {

    eventBus = new EventBus(eventBusName /*, executorService*/);
  }

  @Override
  public EventBus get() {
    return eventBus;
  }
}
