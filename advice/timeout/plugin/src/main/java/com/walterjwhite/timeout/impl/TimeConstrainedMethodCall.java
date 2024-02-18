package com.walterjwhite.timeout.impl;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeConstrainedMethodCall {
  private final static ScheduledExecutorService EXECUTOR_SERVICE = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
  protected final transient Interrupter interrupter = new Interrupter(Thread.currentThread());

  protected final Duration allowedExecutionDuration;
  protected final Future future;

  public TimeConstrainedMethodCall(final Duration allowedExecutionDuration) {
    this.allowedExecutionDuration = allowedExecutionDuration;

    future =
            EXECUTOR_SERVICE.schedule(
            interrupter, allowedExecutionDuration.toNanos(), TimeUnit.NANOSECONDS);
  }

  
  public boolean cancelInterruption() {
    if (!future.isCancelled() && !future.isDone()) {
      return future.cancel(true);
    }

    return true;
  }
}
