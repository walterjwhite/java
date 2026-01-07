package com.walterjwhite.citrix;

import java.util.concurrent.Callable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Util {
  public static <T> T waitForValue(final Callable<T> callable, final long timeout) {
    LOGGER.info("waitForValue: {}", timeout);
    final long endTime = System.currentTimeMillis() + timeout;
    while (System.currentTimeMillis() < endTime) {
      try {
        return callable.call();
      } catch (Exception e) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt(); // Restore interrupted status
        }
      } finally {
        LOGGER.info("waitForValue: {}", timeout);
      }
    }

    LOGGER.info("waitForValue: valueNotMatched: {}", timeout);
    return null;
  }
}
