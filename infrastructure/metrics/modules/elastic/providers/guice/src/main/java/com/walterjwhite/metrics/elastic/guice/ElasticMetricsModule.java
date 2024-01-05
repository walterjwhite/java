package com.walterjwhite.metrics.elastic.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.metrics.modules.elastic.ElasticConfigProvider;
import com.walterjwhite.metrics.modules.elastic.ElasticMeterRegistryProvider;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.elastic.ElasticConfig;

public class ElasticMetricsModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(ElasticConfig.class).toProvider(ElasticConfigProvider.class);
    bind(MeterRegistry.class)
        .toProvider(ElasticMeterRegistryProvider.class) /*.in(Singleton.class)*/;
  }
}
