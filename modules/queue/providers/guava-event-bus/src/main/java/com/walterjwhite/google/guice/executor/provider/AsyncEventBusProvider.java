package com.walterjwhite.google.guice.executor.provider;

import com.google.common.eventbus.AsyncEventBus;
import com.walterjwhite.google.guice.annotation.EventBusOnly;
import com.walterjwhite.job.impl.property.property.AsyncEventBusName;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.concurrent.ExecutorService;

public class AsyncEventBusProvider implements Provider<AsyncEventBus> {
  protected final AsyncEventBus asyncEventBus;

  @Inject
  public AsyncEventBusProvider(
      @EventBusOnly ExecutorService executorService,
      @Property(AsyncEventBusName.class) String asyncEventBusName) {

    asyncEventBus = new AsyncEventBus(asyncEventBusName, executorService);
  }

  @Override
  public AsyncEventBus get() {
    return asyncEventBus;
  }
}
