package com.walterjwhite.keep_alive;

public interface KeepAliveable<Type extends KeepAlive> {
  default void onKeepAlive(Type data) {}
}
