package com.walterjwhite.heartbeat.impl;

import com.walterjwhite.background.BackgroundWorker;
import com.walterjwhite.heartbeat.Heartbeatable;
import java.util.concurrent.*;

/**
 * Executed periodically to show a heartbeat while the method annotated with @Heartbeat is running
 */
public class HeartbeatInstance {
  protected final Heartbeatable heartbeatable;
  protected final ScheduledFuture future;

  public HeartbeatInstance(final Object intercepted) {
    heartbeatable = (Heartbeatable) intercepted;
    future = scheduleHeartbeat();
  }

  /**
   * Attempts to cancel the interruption of the target method.
   *
   * @return false if the task could not be cancelled
   */
  public boolean cancel() {
    if (!future.isCancelled() && !future.isDone()) {
      return future.cancel(true);
    }

    return true;
  }

  /**
   * Schedules the heartbeat with an initial delay and period to be the same value.
   *
   * @return the future queuedJob to cancel the current invocation
   */
  protected ScheduledFuture scheduleHeartbeat() {
    return BackgroundWorker.EXECUTOR_SERVICE.scheduleAtFixedRate(
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
