package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.NonLoggable;
import com.walterjwhite.queue.api.service.ForkJoinWork;
import jakarta.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class HeartbeatExampleCommandLineHandler implements CommandLineHandler, Heartbeatable {
  protected int i = 0;

  @Heartbeat
  @Override
  public void run(String... arguments) throws InterruptedException {
    final ForkJoinWork forkJoinWork = new ForkJoinWork();
    for (int i = 0; i < 10; i++) {
      final BlockingExample blockingExample =
          new BlockingExample(i, Math.round(Math.random() * 10000));
      forkJoinWork.submit(() -> blockingExample.work());
    }

    forkJoinWork.waitForAll(1, TimeUnit.HOURS);
  }

  @NonLoggable
  @Override
  public void onHeartbeat() {}

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
