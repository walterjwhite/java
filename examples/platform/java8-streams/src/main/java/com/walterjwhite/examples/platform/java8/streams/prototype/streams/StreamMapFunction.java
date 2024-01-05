package com.walterjwhite.examples.platform.java8.streams.prototype.streams;

import java.util.function.Function;

public class StreamMapFunction implements Function<String, String> {
  @Override
  public String apply(String s) {
    return s.toUpperCase();
  }
}
