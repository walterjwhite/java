package com.walterjwhite.examples.heartbeat;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import java.time.Duration;
import java.util.concurrent.PriorityBlockingQueue;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class BlockingExample implements Heartbeatable {
  protected final transient PriorityBlockingQueue priorityBlockingQueue =
      new PriorityBlockingQueue();
  protected final int index;

  protected int i = 0;

  @Override
  public void onHeartbeat() {
    // do nothing
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.ofSeconds(1);
  }

  @Heartbeat
  @SneakyThrows
  public void work() {
    priorityBlockingQueue.take();
  }
}
