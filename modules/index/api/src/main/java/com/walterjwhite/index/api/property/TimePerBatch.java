package com.walterjwhite.index.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface TimePerBatch extends ConfigurableProperty {
  // @DefaultValue Duration Default = Duration.ofSeconds(1);
  // time in millis
  @DefaultValue int Default = 1000;
}
