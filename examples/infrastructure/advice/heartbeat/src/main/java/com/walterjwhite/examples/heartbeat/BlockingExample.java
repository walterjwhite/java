package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class BlockingExample implements Heartbeatable {
  protected final int index;
  protected final long delay;

  protected int i = 0;

  @Override
  public void onHeartbeat() {
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.ofSeconds(1);
  }

  @Heartbeat
  @SneakyThrows
  public void work() {
    Thread.sleep(delay);
  }
}
