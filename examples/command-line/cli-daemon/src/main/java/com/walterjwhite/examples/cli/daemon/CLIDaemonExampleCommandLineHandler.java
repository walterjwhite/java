package com.walterjwhite.examples.cli.daemon;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.service.DaemonCommandLineHandler;
import jakarta.inject.Inject;
import java.time.Duration;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class CLIDaemonExampleCommandLineHandler implements DaemonCommandLineHandler, Heartbeatable {
  @Heartbeat
  @Override
  public void run(String... arguments) throws Exception {
    DaemonCommandLineHandler.super.run(arguments);
  }

  @Override
  public void onHeartbeat() {
    LOGGER.info("on heartbeat");
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.ofSeconds(2);
  }
}
