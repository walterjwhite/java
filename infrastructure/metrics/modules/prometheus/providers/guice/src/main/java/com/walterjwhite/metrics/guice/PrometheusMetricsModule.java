package com.walterjwhite.metrics.guice;

import com.google.inject.AbstractModule;
import com.walterjwhite.metrics.modules.prometheus.PrometheusConfigProvider;
import com.walterjwhite.metrics.modules.prometheus.PrometheusHttpServer;
import io.micrometer.prometheusmetrics.PrometheusConfig;

public class PrometheusMetricsModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(PrometheusHttpServer.class).asEagerSingleton();
    bind(PrometheusConfig.class)
        .toProvider(PrometheusConfigProvider.class) /*.in(Singleton.class)*/;
  }
}
