package com.walterjwhite.metrics.modules.elastic;

import io.micrometer.core.instrument.Clock;
import io.micrometer.elastic.ElasticConfig;
import io.micrometer.elastic.ElasticMeterRegistry;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ElasticMeterRegistryProvider implements Provider<ElasticMeterRegistry> {
  protected final ElasticMeterRegistry elasticMeterRegistry;

  @Inject
  public ElasticMeterRegistryProvider(ElasticConfig elasticConfig) {
    elasticMeterRegistry = new ElasticMeterRegistry(elasticConfig, Clock.SYSTEM);
  }

  @Override
  public ElasticMeterRegistry get() {
    return elasticMeterRegistry;
  }
}
