package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.impl.annotation.Property;
import jakarta.inject.Inject;
import java.util.Optional;

@CommandLineHandler
public class CLIExampleCommandLineHandler extends AbstractCommandLineHandler {
  protected final String proxyHost;
  protected final Integer proxyPort;

  @Inject
  public CLIExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(String... arguments) {
    SomeInterface someInterface = SomeInterfaceFactory.getSomeInterface("lower");
  }
}
