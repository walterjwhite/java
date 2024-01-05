package com.walterjwhite.encryption.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface InitializationVectorLength extends ConfigurableProperty {
  int B_8 = 8;
  int B_16 = 16;
  @DefaultValue int B_96 = 96;
}
