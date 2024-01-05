package com.walterjwhite.examples.platform.java8.streams.prototype.email;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class StreamsTest {
  @Test
  void test() {
    List<String> foldersForEmailAccount = Arrays.asList("a1", "a2", "b1", "c2", "c1");

    foldersForEmailAccount.stream()
        .map(new StreamMapFunction())
        .parallel()
        .forEach(new StreamConsumer());
  }
}
