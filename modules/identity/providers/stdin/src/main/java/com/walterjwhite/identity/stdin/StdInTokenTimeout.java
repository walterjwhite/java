package com.walterjwhite.identity.stdin;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface StdInTokenTimeout extends ConfigurableProperty {
  @DefaultValue int Default = 60 * 1000;
}
