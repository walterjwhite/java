package com.walterjwhite.examples.platform.java8.streams.prototype;

import io.reactivex.functions.Consumer;

public class TestConsumer implements Consumer<String> {
  protected final String name;

  public TestConsumer(String name) {
    this.name = name;
  }

  @Override
  public void accept(String s) {}
}
