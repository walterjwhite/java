package com.walterjwhite.metrics.modules.elastic.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.time.Duration;

public interface ElasticStep extends ConfigurableProperty {
  @DefaultValue Duration Default = Duration.ofSeconds(10);
}
