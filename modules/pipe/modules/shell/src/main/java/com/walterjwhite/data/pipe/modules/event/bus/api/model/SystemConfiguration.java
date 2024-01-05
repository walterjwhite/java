package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class SystemConfiguration {
  protected boolean error;

  public SystemConfiguration(boolean error) {

    this.error = error;
  }

  public SystemConfiguration() {
    this(false);
  }
}
