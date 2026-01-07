package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@Deprecated
public interface Threading extends ConfigurableProperty {
  @DefaultValue int DEFAULT = Runtime.getRuntime().availableProcessors() * 2;
}
