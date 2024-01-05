package com.walterjwhite.examples.interruption.basic;

import com.walterjwhite.inject.cli.property.CommandLineHandlerShutdownTimeout;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;

// this doesn't show anything, run() blocks
@Deprecated
public class BasicInterruptionExampleCommandLineHandler /*extends AbstractCommandLineHandler*/ {
  @Inject
  public BasicInterruptionExampleCommandLineHandler(
      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
    // super(shutdownTimeoutInSeconds);
  }

  //  public static void main(final String[] arguments) throws IOException, InterruptedException {}

  // @Override
  public void run(String... arguments) throws Exception {
    final ExampleWorker w1 = new ExampleWorker();
    w1.run();

    w1.kill();
  }
}
