package com.walterjwhite.keep_alive;

import java.time.Duration;

public interface KeepAlive {

  /**
   * The interval for which to invoke onKeepAlive().
   *
   * @return
   */
  default Duration getKeepAliveInterval() {
    return Duration.ofMinutes(5);
  }

  /**
   * The duration for which to invoke onKeepAlive().
   *
   * @return
   */
  default Duration getKeepAliveDuration() {
    return Duration.ofHours(1);
  }
}
