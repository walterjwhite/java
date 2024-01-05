package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.ShellSink;
import com.walterjwhite.shell.api.model.ShellCommand;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class ShellSinkConfiguration extends AbstractSinkConfiguration {
  protected ShellCommand shellCommand;

  public ShellSinkConfiguration(ShellCommand shellCommand) {
    this();
    this.shellCommand = shellCommand;
  }

  public ShellSinkConfiguration() {
    super(ShellSink.class);
  }
}
