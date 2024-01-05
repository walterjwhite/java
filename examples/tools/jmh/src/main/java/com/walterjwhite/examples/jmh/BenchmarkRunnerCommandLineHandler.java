package com.walterjwhite.examples.jmh;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.inject.cli.service.AbstractCommandLineHandler;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import org.openjdk.jmh.Main;

public class BenchmarkRunnerCommandLineHandler extends AbstractCommandLineHandler {
  @Inject
  public BenchmarkRunnerCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    super(shutdownTimeoutInSeconds);
  }

  @Override
  public void run(String... arguments) throws Exception {
    Main.main(arguments);
  }
}
