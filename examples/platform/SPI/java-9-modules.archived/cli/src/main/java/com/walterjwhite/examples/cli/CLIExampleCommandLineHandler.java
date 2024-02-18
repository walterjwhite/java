package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;

@CommandLineHandler
public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(String... arguments) {
    final ServiceLoader<SomeInterface> services = ServiceLoader.load(SomeInterface.class);
    for (final SomeInterface service : services) {
          "service " + service.getClass().getSimpleName() + " : " + service.process("SPI"));
    }
  }
}
