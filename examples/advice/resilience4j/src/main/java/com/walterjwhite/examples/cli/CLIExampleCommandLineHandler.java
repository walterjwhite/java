package com.walterjwhite.examples.cli;

import com.walterjwhite.google.resilience4j.annotation.Bulkhead;
import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CLIExampleCommandLineHandler implements CommandLineHandler, Heartbeatable {
  @Override
  public void run(String... arguments) throws InterruptedException {
    runExceptionHandlingTest(arguments);
  }

  @Heartbeat
  protected void runHeartbeatTest() throws InterruptedException {
    Thread.sleep(60 * 1000);
  }

  protected void runExceptionHandlingTest(final String... arguments) {
    for (int i = 0; i < 100; i++) {
      try {
        doRun(arguments);
      } catch (Exception e) {
        LOGGER.warn("caught exception", e);
      }
    }
  }

  @Bulkhead
  protected void doRun(final String... arguments) {
    throw new RuntimeException("test exception");
  }

  @Override
  public void onHeartbeat() {
    LOGGER.info("onHeartbeat");
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.ofMillis(250);
  }
}
