package com.walterjwhite.delay;

import java.time.Duration;
import java.util.Optional;

public interface RandomDelayable extends Delayable {
  Duration getMinimum();

  Duration getMaximum();

  default Optional<Duration> get() {
    if (getMinimum() == null) {
      return Optional.empty();
    }

    if (getMaximum() == null) {
      return Optional.empty();
    }

    return Optional.of(
        getMinimum()
            .plus(
                Duration.ofMillis(
                    Math.round(Math.random() * getMaximum().minus(getMinimum()).toMillis()))));
  }
}
