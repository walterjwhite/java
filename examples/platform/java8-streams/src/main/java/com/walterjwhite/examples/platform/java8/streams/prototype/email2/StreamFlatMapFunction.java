package com.walterjwhite.examples.platform.java8.streams.prototype.email2;

import java.util.function.Function;
import java.util.stream.Stream;

public class StreamFlatMapFunction implements Function<String, Stream<String>> {
  @Override
  public Stream<String> apply(String s) {
    // return Stream.of(/*s.toCharArray()*/s.split(""));
    return Stream.of(s + "/Message 1", s + "/Message 2", s + "/Message 3");
  }
}
