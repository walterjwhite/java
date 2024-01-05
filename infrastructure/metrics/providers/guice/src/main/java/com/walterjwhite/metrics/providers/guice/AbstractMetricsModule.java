package com.walterjwhite.infrastructure.metrics.api;

import com.google.inject.AbstractModule;

public abstract class AbstractMetricsModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(MetricsService.class).asEagerSingleton();
  }
}
