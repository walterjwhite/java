package com.walterjwhite.examples.interruption;

import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * Pressing CTRL+C results in a waitFor returning an exit code of 130. Additionally, the process is
 * killed first, the interrupt isn't invoked. Killing the java process results in waitFor returning
 * an exit code of 143, only after the interrupt method kills the process.
 */
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
