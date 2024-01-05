package com.walterjwhite.metrics.modules.prometheus.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.Duration;

@PropertyValueType(Duration.class)
public interface NetflixStep extends ConfigurableProperty {
  @DefaultValue Duration Default = Duration.ofSeconds(10);
}
