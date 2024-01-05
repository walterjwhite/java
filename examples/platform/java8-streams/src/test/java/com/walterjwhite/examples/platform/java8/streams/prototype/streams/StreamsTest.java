package com.walterjwhite.examples.platform.java8.streams.prototype.streams;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class StreamsTest {
  @Test
  void test() {
    List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    myList.stream()
        .filter(new StreamFilterPredicate())
        .map(new StreamMapFunction())
        .sorted()
        .forEach(new StreamConsumer());
  }
}
