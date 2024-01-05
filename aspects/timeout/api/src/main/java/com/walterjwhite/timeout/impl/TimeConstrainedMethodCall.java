package com.walterjwhite.timeout.impl;

import com.walterjwhite.background.BackgroundWorker;
import java.time.Duration;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TimeConstrainedMethodCall {
  protected final transient Interrupter interrupter = new Interrupter(Thread.currentThread());

  protected final Duration allowedExecutionDuration;
  protected final Future future;

  public TimeConstrainedMethodCall(final Duration allowedExecutionDuration) {
    this.allowedExecutionDuration = allowedExecutionDuration;

    future =
        BackgroundWorker.EXECUTOR_SERVICE.schedule(
            interrupter, allowedExecutionDuration.toNanos(), TimeUnit.NANOSECONDS);
  }

  /**
   * Attempts to cancel the interruption of the target method.
   *
   * @return false if the task could not be cancelled
   */
  public boolean cancelInterruption() {
    if (!future.isCancelled() && !future.isDone()) {
      return future.cancel(true);
    }

    return true;
  }
}
