package com.walterjwhite.keep_alive;

import java.time.Duration;

public interface KeepAlive {

  default Duration getKeepAliveInterval() {
    return Duration.ofMinutes(5);
  }

  default Duration getKeepAliveDuration() {
    return Duration.ofHours(1);
  }
}
