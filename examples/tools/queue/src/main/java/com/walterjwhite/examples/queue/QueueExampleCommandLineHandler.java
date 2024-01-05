package com.walterjwhite.examples.queue;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class QueueExampleCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public QueueExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) throws InterruptedException {
    Thread.sleep(60000);
  }
}
