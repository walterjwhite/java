package com.walterjwhite.examples.interruption;

import java.util.concurrent.Callable;

public class InterruptableTaskExample implements AutoCloseable, Callable<Void> {
  protected final int maxCount;
  protected boolean interrupted = false;

  public InterruptableTaskExample(int maxCount) {
    this.maxCount = maxCount;
  }

  public Void call() {
    int i = 0;
    while (!interrupted && i < maxCount) {}

    return null;
  }

  @Override
  public void close() {
    interrupted = true;
  }
}
