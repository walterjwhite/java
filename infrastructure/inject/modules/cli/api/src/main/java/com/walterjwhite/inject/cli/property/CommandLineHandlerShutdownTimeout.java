package com.walterjwhite.inject.cli.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@PropertyValueType(int.class)
public interface CommandLineHandlerShutdownTimeout extends ConfigurableProperty {
  @DefaultValue int Default = 30; 




}
