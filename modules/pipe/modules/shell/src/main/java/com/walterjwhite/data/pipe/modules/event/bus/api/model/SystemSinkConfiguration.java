package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.SystemSink;
import lombok.Getter;

@Getter
public class SystemSinkConfiguration extends AbstractSinkConfiguration {
  protected final boolean error;

  public SystemSinkConfiguration(boolean error) {
    super(SystemSink.class);
    this.error = error;
  }
}
