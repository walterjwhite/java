package com.walterjwhite.metrics.modules.prometheus;

import com.netflix.spectator.atlas.AtlasConfig;
import com.walterjwhite.infrastructure.metrics.api.AbstractMetricsModule;
import io.micrometer.core.instrument.MeterRegistry;

public class NetflixAtlasMetricsModule extends AbstractMetricsModule {

  @Override
  protected void configure() {
    super.configure();

    bind(AtlasConfig.class).toProvider(AtlasConfigProvider.class);
    bind(MeterRegistry.class).toProvider(AtlasMeterRegistryProvider.class) /*.in(Singleton.class)*/;
  }
}
