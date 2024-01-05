package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;

public enum PipeOperatingMode implements OperatingMode {
  @DefaultValue
  Daemon("Connect sources with sinks and run until killed", PipeCommandLineHandler.class);

  private final String description;
  private final Class<? extends CommandLineHandler> commandLineHandlerClass;

  PipeOperatingMode(
      String description, Class<? extends CommandLineHandler> commandLineHandlerClass) {
    this.description = description;
    this.commandLineHandlerClass = commandLineHandlerClass;
  }

  @Override
  public Class<? extends CommandLineHandler> getInitiatorClass() {
    return commandLineHandlerClass;
  }
}
