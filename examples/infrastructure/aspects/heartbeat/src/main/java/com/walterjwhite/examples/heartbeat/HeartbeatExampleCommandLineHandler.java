package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.queue.api.service.ForkJoinWork;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class HeartbeatExampleCommandLineHandler implements CommandLineHandler, Heartbeatable {
  protected int i = 0;

  //  @Inject
  //  public HeartbeatExampleCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Heartbeat
  @Override
  public void run(String... arguments) throws InterruptedException {
    //    Thread.sleep(5 * 60 * 1000);
    final ForkJoinWork forkJoinWork = new ForkJoinWork();
    for (int i = 0; i < 10; i++) {
      final BlockingExample blockingExample = new BlockingExample(i);
      forkJoinWork.submit(() -> blockingExample.work());
    }

    forkJoinWork.waitForAll(1, TimeUnit.HOURS);
  }

  // test if non-loggable annotation works, yes
  //  @NonLoggable
  @Override
  public void onHeartbeat() {}

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.of(1, ChronoUnit.SECONDS);
  }
}
