package com.walterjwhite.ssh.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface InterruptGracePeriodValue extends ConfigurableProperty {
  @DefaultValue long Default = 5;
}
