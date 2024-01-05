package com.walterjwhite.examples.platform.java8.streams.prototype.streams;

import java.util.function.Predicate;

public class StreamFilterPredicate implements Predicate<String> {
  @Override
  public boolean test(String s) {
    return s.startsWith("c");
  }
}
