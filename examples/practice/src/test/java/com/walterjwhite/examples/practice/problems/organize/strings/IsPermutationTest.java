package com.walterjwhite.examples.practice.problems.organize.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsPermutationTest {
  @Test
  void testBothNull() {
    Assertions.assertFalse(IsPermutation.IsPermutation(null, null));
  }

  @Test
  void testLeftNull() {
    Assertions.assertFalse(IsPermutation.IsPermutation(null, "abcd"));
  }

  @Test
  void testRightNull() {
    Assertions.assertFalse(IsPermutation.IsPermutation("abcd", null));
  }

  @Test
  void testPermutation1() {
    Assertions.assertTrue(IsPermutation.IsPermutation("abcd", "bcda"));
  }

  @Test
  void testPermutation2() {
    Assertions.assertTrue(IsPermutation.IsPermutation("dcba", "bcda"));
  }
}
