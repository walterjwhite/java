package com.walterjwhite.metrics.modules.prometheus;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

@Singleton
public class PrometheusMeterRegistryProvider implements Provider<PrometheusMeterRegistry> {
  protected final PrometheusMeterRegistry prometheusMeterRegistry;

  @Inject
  public PrometheusMeterRegistryProvider(PrometheusConfig prometheusConfig) {
    prometheusMeterRegistry = new PrometheusMeterRegistry(prometheusConfig);
  }

  @Produces
  @Singleton
  @Override
  public PrometheusMeterRegistry get() {
    return prometheusMeterRegistry;
  }
}
