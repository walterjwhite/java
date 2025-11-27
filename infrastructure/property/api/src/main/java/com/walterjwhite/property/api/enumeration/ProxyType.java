package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@Optional
public enum ProxyType implements ConfigurableProperty {
  @DefaultValue
  HTTP,
  SOCKS
}
