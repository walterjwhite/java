package com.walterjwhite.closeable.runtime;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class CloseableShutdownHook extends Thread {
  protected final Set<AutoCloseable> autoCloseables = new HashSet<>();

  @Override
  public void run() {
    autoCloseables.forEach(autoCloseable -> doClose(autoCloseable));
  }

  protected void doClose(final AutoCloseable autoCloseable) {
    try {
      autoCloseable.close();
    } catch (Exception e) {
      LOGGER.info(String.format("Error closing: %s", autoCloseable), e);
    }
  }
}
