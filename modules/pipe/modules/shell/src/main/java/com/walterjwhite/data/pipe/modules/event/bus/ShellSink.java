package com.walterjwhite.data.pipe.modules.event.bus;

import com.walterjwhite.data.pipe.impl.AbstractSink;
import com.walterjwhite.data.pipe.modules.event.bus.api.model.ShellSinkConfiguration;
import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;

public class ShellSink extends AbstractSink<String, ShellSinkConfiguration> {
  protected final ShellExecutionService shellExecutionService;

  @Inject
  public ShellSink(ShellExecutionService shellExecutionService) {

    this.shellExecutionService = shellExecutionService;
  }

  @Override
  protected void doConfigure() {}

  @Override
  public void accept(String record) {
    try {
      shellExecutionService.run(new ShellCommand().withCommandLine(record));
    } catch (Exception e) {
      throw new RuntimeException("Error running command", e);
    }
  }

  public void close() {
    // shellExecutionService.kill(shellCommand);
  }
}
