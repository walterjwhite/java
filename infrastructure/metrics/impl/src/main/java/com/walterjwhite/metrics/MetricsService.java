package com.walterjwhite.metrics;

import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import com.walterjwhite.metrics.api.MetricsRegistryHelper;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class MetricsService implements StartupAware, AutoCloseable {
  protected final MeterRegistry meterRegistry;

  @Override
  public void startup() {
    MetricsRegistryHelper.setMeterRegistry(meterRegistry);
  }

  @Override
  public void close() {
    MetricsRegistryHelper.setMeterRegistry(null);

    meterRegistry.close();
  }
}
