package com.walterjwhite.examples.practice.problems.organize.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SringReverserTest {
  @Test
  void testNull() {
    Assertions.assertTrue(StringReverser.reverse(null) == null);
  }

  @Test
  void testSingleCharacter() {
    final String input = "a";
    Assertions.assertEquals(StringReverser.reverse(input), input);
  }

  @Test
  void test2Characters() {
    final String input = "ab";
    Assertions.assertEquals(StringReverser.reverse(input), "ba");
  }
}
