package com.walterjwhite.index.modules.jpa.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface IndexTimeoutValue extends /*TimeoutValue*/ ConfigurableProperty {
  @DefaultValue int Default = 1000;
}
