package com.walterjwhite.examples.contextual_loggable;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class ContextualLoggableExampleCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public ContextualLoggableExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) {
    new ContextualWorker(arguments).doSomething();

    new FieldContextualWorker(arguments).doSomething();
  }
}
