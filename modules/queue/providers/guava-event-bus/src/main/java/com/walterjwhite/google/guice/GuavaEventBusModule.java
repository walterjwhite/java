package com.walterjwhite.google.guice;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.walterjwhite.google.guice.annotation.EventBusOnly;
import com.walterjwhite.google.guice.executor.provider.AsyncEventBusProvider;
import com.walterjwhite.google.guice.executor.provider.EventBusExecutorServiceProvider;
import com.walterjwhite.google.guice.executor.provider.EventBusProvider;
import jakarta.inject.Singleton;
import java.util.concurrent.ExecutorService;

public class GuavaEventBusModule extends AbstractModule {
  protected final EventBusDelegate eventBusDelegate = new EventBusDelegate();

  @Override
  protected void configure() {
    bind(AsyncEventBus.class)
        .annotatedWith(EventBusOnly.class)
        .toProvider(AsyncEventBusProvider.class)
        .in(Singleton.class);
    bind(EventBus.class).toProvider(EventBusProvider.class).in(Singleton.class);
    bind(EventBusDelegate.class).toInstance(eventBusDelegate);

    bind(ListeningScheduledExecutorService.class)
        .annotatedWith(EventBusOnly.class)
        .toProvider(EventBusExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ListeningExecutorService.class)
        .annotatedWith(EventBusOnly.class)
        .toProvider(EventBusExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ExecutorService.class)
        .annotatedWith(EventBusOnly.class)
        .toProvider(EventBusExecutorServiceProvider.class)
        .in(Singleton.class);

    bindListener(Matchers.any(), new GuavaSubscriberTypeListener(eventBusDelegate));
  }
}
