package com.walterjwhite.keep_alive;

public interface KeepAliveable<Type extends KeepAlive> {
  /**
   * Invoked every getKeepAliveInterval() until the original invocation ends either successfully or
   * on error.
   */
  default void onKeepAlive(Type data) {}
}
