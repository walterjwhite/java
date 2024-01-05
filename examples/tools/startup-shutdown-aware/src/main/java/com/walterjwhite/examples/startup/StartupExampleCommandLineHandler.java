package com.walterjwhite.examples.startup;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class StartupExampleCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public StartupExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) throws InterruptedException {
    // create a new instance, it will automatically be cleaned up ...
    new ShutdownAwareExample();

    Thread.sleep(1000);
  }
}
