package com.walterjwhite.examples.interruption;

import java.util.concurrent.*;

public class InterruptableServiceExample implements AutoCloseable {
  protected final ExecutorService executorService =
      Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

  public Future run(final Callable callable) {
    return executorService.submit(callable);
  }

  @Override
  public void close() {
    executorService.shutdown();
  }
}
