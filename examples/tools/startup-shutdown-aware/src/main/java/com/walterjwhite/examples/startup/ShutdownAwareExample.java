package com.walterjwhite.examples.startup;

public class ShutdownAwareExample implements AutoCloseable {
  @Override
  public void close() {}
}
