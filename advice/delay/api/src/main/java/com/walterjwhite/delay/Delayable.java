package com.walterjwhite.delay;

import java.time.Duration;
import java.util.Optional;

public interface Delayable {
  default boolean isEnabled() {
    return true;
  }

  Optional<Duration> get();

  default void delay() throws InterruptedException {
    if (isEnabled()) {
      final Optional<Duration> durationOptional = get();
      if (durationOptional.isPresent()) {
        Thread.sleep(durationOptional.get().toMillis());
      }
    }
  }
}
