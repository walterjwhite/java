package com.walterjwhite.heartbeat;

import java.time.Duration;

public interface Heartbeatable {
  default void onHeartbeat() {}

  Duration getHeartbeatInterval();

  default Duration getHeartbeatDuration() {
    return null;
  }
}
