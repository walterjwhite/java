package com.walterjwhite.examples.contextual_loggable;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class ContextualLoggableExampleCommandLineHandler implements CommandLineHandler {
  @Override
  public void run(String... arguments) {
    try {
      new ContextualWorker(arguments).doSomething();
    } catch (Exception e) {

    }

    try {
      new FieldContextualWorker(arguments).doSomething();
    } catch (Exception e) {

    }
  }
}
