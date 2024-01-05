package com.walterjwhite.metrics.modules.prometheus.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.Duration;

/** This does NOT work? */
public interface PrometheusStep extends ConfigurableProperty {
  @DefaultValue Duration Default = Duration.ofMinutes(1);
}
