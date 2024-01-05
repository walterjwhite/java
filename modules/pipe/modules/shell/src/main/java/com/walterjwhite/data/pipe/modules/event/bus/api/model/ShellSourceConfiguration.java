package com.walterjwhite.data.pipe.modules.event.bus.api.model;

import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.modules.event.bus.ShellSource;
import com.walterjwhite.shell.api.model.ShellCommand;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class ShellSourceConfiguration extends AbstractSourceConfiguration {
  protected ShellCommand shellCommand;

  public ShellSourceConfiguration(ShellCommand shellCommand) {
    this();
    this.shellCommand = shellCommand;
  }

  public ShellSourceConfiguration() {
    super(ShellSource.class);
  }
}
