package com.walterjwhite.metrics.modules.prometheus.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface PrometheusServerContextPath extends ConfigurableProperty {
  @DefaultValue String Default = "/metrics";
}
