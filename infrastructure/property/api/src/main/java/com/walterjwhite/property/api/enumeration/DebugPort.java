package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@Deprecated
public interface DebugPort extends ConfigurableProperty {
  @DefaultValue int DEFAULT = 1234;
}
