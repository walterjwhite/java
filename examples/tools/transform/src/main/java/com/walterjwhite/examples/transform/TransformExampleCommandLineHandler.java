package com.walterjwhite.examples.transform;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class TransformExampleCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public TransformExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) {
    LOGGER.debug("TransformExampleCommandLineHandler.testMethod: {}", testMethod("Bob", "Shelley"));

    LOGGER.debug(
        "TransformExampleCommandLineHandler.NonDIObject.testMethod: {}",
        NonDIObject.testMethod("Bob", "Shelley"));
    LOGGER.debug(
        "TransformExampleCommandLineHandler.NonDIObject.hashAnotherMethod: {}",
        NonDIObject.hashAnotherMethod("Bob", "Shelley"));
    LOGGER.debug(
        "TransformExampleCommandLineHandler.NonDIObject.testMethodKeyValue: {}",
        NonDIObject.testMethodKeyValue("Bob", "Shelley"));

    LOGGER.debug("TransformExampleCommandLineHandler.SomeComplexObject.build: {}", build());
  }

  // *MUST* be public
  public String testMethod(final String argument1, final String argument2) {
    return "ORIGINAL METHOD: " + argument1 + ":" + argument2;
  }

  public SomeComplexObject build() {
    return new SomeComplexObject(
        "Fred",
        new Address("line 1", "line 2", "line 3", "city", "state", "country", "postalCode"));
  }
}
