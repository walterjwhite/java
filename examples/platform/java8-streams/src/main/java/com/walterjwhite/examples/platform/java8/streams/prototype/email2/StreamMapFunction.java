package com.walterjwhite.examples.platform.java8.streams.prototype.email2;

import java.util.function.Function;

public class StreamMapFunction implements Function<String, char[]> {
  @Override
  public char[] apply(String s) {
    return s.toCharArray();
  }
}
