package com.walterjwhite.encryption.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface HashIterations extends ConfigurableProperty {
  //  1000,
  // 5000,
  // 50000;
  @DefaultValue int Default = 50000;
}
