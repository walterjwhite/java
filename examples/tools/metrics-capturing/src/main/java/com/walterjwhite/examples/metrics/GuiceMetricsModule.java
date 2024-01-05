package com.walterjwhite.examples.metrics;

import com.google.inject.AbstractModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;
import com.walterjwhite.metrics.guice.PrometheusMetricsModule;

public class GuiceMetricsModule extends AbstractModule implements GuiceApplicationModule {
  @Override
  protected void configure() {
    bind(ExampleService.class);

    install(new PrometheusMetricsModule());
  }
}
