package com.walterjwhite.google.guice.executor;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.inject.AbstractModule;
import com.walterjwhite.google.guice.executor.annotation.ExecutorManagementServiceOnly;
import com.walterjwhite.google.guice.executor.annotation.ExecutorServiceOnly;
import com.walterjwhite.google.guice.executor.provider.ExecutorServiceProvider;
import jakarta.inject.Singleton;
import java.util.concurrent.ExecutorService;

public class GuavaExecutorServiceModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(ListeningScheduledExecutorService.class)
        .annotatedWith(ExecutorServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ListeningExecutorService.class)
        .annotatedWith(ExecutorServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ExecutorService.class)
        .annotatedWith(ExecutorServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);

    bind(ListeningScheduledExecutorService.class)
        .annotatedWith(ExecutorManagementServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ListeningExecutorService.class)
        .annotatedWith(ExecutorManagementServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);
    bind(ExecutorService.class)
        .annotatedWith(ExecutorManagementServiceOnly.class)
        .toProvider(ExecutorServiceProvider.class)
        .in(Singleton.class);
  }
}
