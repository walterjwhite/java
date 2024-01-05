package com.walterjwhite.metrics.modules.prometheus;

import com.netflix.spectator.atlas.AtlasConfig;
import io.micrometer.atlas.AtlasMeterRegistry;
import io.micrometer.core.instrument.Clock;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class AtlasMeterRegistryProvider implements Provider<AtlasMeterRegistry> {
  protected final AtlasMeterRegistry atlasMeterRegistry;

  @Inject
  public AtlasMeterRegistryProvider(AtlasConfig atlasConfig) {
    atlasMeterRegistry = new AtlasMeterRegistry(atlasConfig, Clock.SYSTEM);
  }

  @Override
  public AtlasMeterRegistry get() {
    return atlasMeterRegistry;
  }
}
