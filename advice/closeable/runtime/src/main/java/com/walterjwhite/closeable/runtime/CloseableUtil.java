package com.walterjwhite.closeable.runtime;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CloseableUtil {
  private static final CloseableShutdownHook CLOSEABLE_SHUTDOWN_HOOK = new CloseableShutdownHook();

  static {
    registerShutdownHook();
  }

  private static void registerShutdownHook() {
    Runtime.getRuntime().addShutdownHook(CLOSEABLE_SHUTDOWN_HOOK);
  }

  public static void addAutoCloseable(final AutoCloseable autoCloseable) {
    CLOSEABLE_SHUTDOWN_HOOK.getAutoCloseables().add(autoCloseable);
  }
}
