package com.walterjwhite.examples.cli.daemon;

import com.walterjwhite.inject.cli.service.DaemonCommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class CLIDaemonExampleCommandLineHandler implements DaemonCommandLineHandler {

  //  @Inject
  //  public CLIDaemonExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }
}
