package com.walterjwhite.google.guice.executor.provider;

import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.walterjwhite.job.impl.property.property.NumberOfEventBusThreads;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.concurrent.Executors;

public class EventBusExecutorServiceProvider
    implements Provider<ListeningScheduledExecutorService> {
  protected final ListeningScheduledExecutorService executorService;

  @Inject
  public EventBusExecutorServiceProvider(
      @Property(NumberOfEventBusThreads.class) int numberOfThreads) {

    executorService =
        MoreExecutors.listeningDecorator(Executors.newScheduledThreadPool(numberOfThreads));
    Runtime.getRuntime().addShutdownHook(new ShutdownHook());
  }

  @Override
  public ListeningScheduledExecutorService get() {
    return executorService;
  }

  class ShutdownHook extends Thread {
    @Override
    public void run() {
      executorService.shutdownNow();
    }
  }
}
