package com.walterjwhite.examples.platform.java8.streams.prototype.email2;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class StreamsTest2 {
  @Test
  void test() {
    List<String> foldersForEmailAccount =
        Arrays.asList("INBOX", "DRAFTS", "SENT", "TRASH", "FAMILY");

    foldersForEmailAccount.stream()
        .flatMap(new StreamFlatMapFunction())
        .parallel()
        .forEach(new StreamConsumer());
  }
}
