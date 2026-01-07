package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;

@Deprecated
public interface ServiceStopTimeout extends ConfigurableProperty {
  @DefaultValue int Default = 30;
}
