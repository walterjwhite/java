package com.walterjwhite.examples.keep_alive;

import com.walterjwhite.keep_alive.KeepAlive;
import java.time.Duration;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class KeepAliveExample implements KeepAlive {
  protected final String data;

  @Override
  public Duration getKeepAliveInterval() {
    return Duration.ofSeconds(1);
  }

  @Override
  public Duration getKeepAliveDuration() {
    return Duration.ofSeconds(5);
  }
}
