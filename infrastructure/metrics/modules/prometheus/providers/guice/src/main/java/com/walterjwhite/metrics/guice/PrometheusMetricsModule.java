package com.walterjwhite.metrics.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.metrics.modules.prometheus.PrometheusConfigProvider;
import com.walterjwhite.metrics.modules.prometheus.PrometheusHttpServer;
import com.walterjwhite.metrics.modules.prometheus.PrometheusMeterRegistryProvider;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import jakarta.inject.Singleton;

public class PrometheusMetricsModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(PrometheusHttpServer.class).asEagerSingleton();
    bind(PrometheusConfig.class)
        .toProvider(PrometheusConfigProvider.class) /*.in(Singleton.class)*/;
    //    bind(PrometheusMeterRegistry.class)
    //        .toProvider(PrometheusMeterRegistryProvider.class)
    //        .in(Singleton.class);
    bind(MeterRegistry.class).toProvider(PrometheusMeterRegistryProvider.class).in(Singleton.class);
    bind(PrometheusMeterRegistry.class)
        .toProvider(PrometheusMeterRegistryProvider.class)
        .in(Singleton.class);
  }
}
