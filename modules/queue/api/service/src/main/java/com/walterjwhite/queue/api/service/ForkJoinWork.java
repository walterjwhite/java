package com.walterjwhite.queue.api.service;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ForkJoinWork implements AutoCloseable {
  protected final ForkJoinPool forkJoinPool = new ForkJoinPool();

  public Future submit(final Runnable runnable) {
    return forkJoinPool.submit(runnable);
  }

  public void waitForAll(final long amount, final TimeUnit timeUnit) throws InterruptedException {
    close();
    forkJoinPool.awaitTermination(amount, timeUnit);
  }

  public void interrupt() {
    if (!forkJoinPool.isShutdown()) {
      forkJoinPool.shutdownNow();
    }
  }

  @Override
  public void close() {
    if (!forkJoinPool.isShutdown()) {
      forkJoinPool.shutdown();
    }
  }
}
