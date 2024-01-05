package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface TodoMoveLongRunningQueryTimeoutValue extends ConfigurableProperty {
  @DefaultValue int Default = 60;
}
