package com.walterjwhite.examples.practice.problems.organize.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompressorTest {
  @Test
  void testNull() {
    Assertions.assertEquals(null, Compressor.compress(null));
  }

  @Test
  void testSingleCharacter() {
    final String input = "A";
    Assertions.assertEquals(input, Compressor.compress(input));
  }

  @Test
  void test2() {
    final String input = "Aa";
    Assertions.assertEquals(input, Compressor.compress(input));
  }

  @Test
  void testAaaaaa() {
    final String input = "Aaaaaa";
    Assertions.assertEquals("A1a5", Compressor.compress(input));
  }

  @Test
  void testaabcccccaaa() {
    final String input = "aabcccccaaa";
    Assertions.assertEquals("a2b1c5a3", Compressor.compress(input));
  }
}
