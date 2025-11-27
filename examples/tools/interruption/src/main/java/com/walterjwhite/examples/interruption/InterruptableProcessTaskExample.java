package com.walterjwhite.examples.interruption;

import java.io.IOException;
import java.util.concurrent.Callable;

public class InterruptableProcessTaskExample implements AutoCloseable, Callable<Void> {
  protected Process process;

  public Void call() {
    try {
      process = Runtime.getRuntime().exec("sleep 600");
      return null;
    } catch (IOException e) {
      throw new RuntimeException("Error during execution", e);
    }
  }

  @Override
  public void close() {
    if (process != null && process.isAlive()) {
      process.destroy();
    }
  }
}
