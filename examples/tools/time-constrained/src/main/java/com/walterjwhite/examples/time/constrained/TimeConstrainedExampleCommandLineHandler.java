package com.walterjwhite.examples.time.constrained;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class TimeConstrainedExampleCommandLineHandler
    implements CommandLineHandler, TimeConstrainedMethodInvocation {


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
