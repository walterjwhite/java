package com.walterjwhite.examples.metrics;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class MetricsCapturingExampleCommandLineHandler implements CommandLineHandler {
  protected final ExampleService exampleService;

  public static final int MAX_ITERATIONS = Integer.MAX_VALUE;

  @Inject
  public MetricsCapturingExampleCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      ExampleService exampleService) {
    //    super(shutdownTimeoutInSeconds);
    this.exampleService = exampleService;
  }

  @Override
  public void run(String... arguments) {
    int i = 0;
    while (i < MAX_ITERATIONS) {
      exampleService.doSomething();
      i++;
    }
  }
}
