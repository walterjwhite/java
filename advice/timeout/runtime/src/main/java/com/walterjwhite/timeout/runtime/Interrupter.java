package com.walterjwhite.timeout.runtime;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class Interrupter implements Runnable {
  protected final transient Thread thread;

  @Override
  public void run() {
    if ("main".equalsIgnoreCase(thread.getName())) {
      LOGGER.error("Interrupting main thread by exiting");
      System.exit(1);
    }

    if (thread.isAlive()) {
      LOGGER.debug("interrupting worker thread");
      thread.interrupt();
    }

    LOGGER.debug("interrupted");
  }
}
