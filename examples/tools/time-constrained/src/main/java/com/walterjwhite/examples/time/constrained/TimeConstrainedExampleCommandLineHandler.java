package com.walterjwhite.examples.time.constrained;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class TimeConstrainedExampleCommandLineHandler
    implements CommandLineHandler, TimeConstrainedMethodInvocation {

  //  @Inject
  //  public TimeConstrainedExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @TimeConstrained
  @Override
  public void run(String... arguments) throws InterruptedException {
    int i = 0;

    while (true) {
      Thread.sleep(50);
    }
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
