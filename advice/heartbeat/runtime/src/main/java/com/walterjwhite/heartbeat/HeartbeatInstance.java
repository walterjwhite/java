package com.walterjwhite.heartbeat.runtime;

import com.walterjwhite.heartbeat.Heartbeatable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class HeartbeatInstance {
  private static final ScheduledExecutorService EXECUTOR_SERVICE =
      Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

  protected final Heartbeatable heartbeatable;
  protected final ScheduledFuture future;

  public HeartbeatInstance(final Object intercepted) {
    heartbeatable = (Heartbeatable) intercepted;
    future = scheduleHeartbeat();
  }

  public boolean cancel() {
    if (!future.isCancelled() && !future.isDone()) {
      return future.cancel(true);
    }

    return true;
  }

  protected ScheduledFuture scheduleHeartbeat() {
    return EXECUTOR_SERVICE.scheduleAtFixedRate(
        new HeartbeatRunnable(),
        heartbeatable.getHeartbeatInterval().getSeconds(),
        heartbeatable.getHeartbeatInterval().getSeconds(),
        TimeUnit.SECONDS);
  }

  private class HeartbeatRunnable implements Runnable {
    @Override
    public void run() {
      heartbeatable.onHeartbeat();
    }
  }
}
