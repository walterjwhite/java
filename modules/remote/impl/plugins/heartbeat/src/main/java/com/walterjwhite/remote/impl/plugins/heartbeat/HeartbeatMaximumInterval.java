package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface HeartbeatMaximumInterval extends ConfigurableProperty {
  @DefaultValue int Default = 60;
}
