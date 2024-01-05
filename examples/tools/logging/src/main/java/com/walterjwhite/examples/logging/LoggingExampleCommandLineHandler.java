package com.walterjwhite.examples.logging;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.NonLoggable;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class LoggingExampleCommandLineHandler implements CommandLineHandler, Heartbeatable {
  protected int i = 0;

  //  @Inject
  //  public LoggingExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Heartbeat
  @Override
  public void run(String... arguments) {

    while (true) {
      i++;

      if (i == 4) {
        throw new IllegalStateException("This is a test of the emergency broadcast system ...");
      }
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }
  }

  // test if non-loggable annotation works, yes
  @NonLoggable
  @Override
  public void onHeartbeat() {}

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
