package com.walterjwhite.examples.platform.java8.streams.prototype.email;

import java.util.function.Function;

public class StreamMapFunction implements Function<String, String[]> {
  @Override
  public String[] apply(String s) {
    return s.split("c");
  }
}
