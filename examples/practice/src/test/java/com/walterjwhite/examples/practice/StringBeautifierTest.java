package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.organize.StringBeautificationCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringBeautifierTest {
  protected final StringBeautificationCounter stringBeautificationCounter =
      new StringBeautificationCounter();

  @Test
  void test_bcbb() {
    Assertions.assertEquals(2, stringBeautificationCounter.numberOfOperations("bcbb"));
  }

  @Test
  void test_efgh() {
    Assertions.assertEquals(2, stringBeautificationCounter.numberOfOperations("efgh"));
  }

  @Test
  void test_azgi() {
    Assertions.assertEquals(0, stringBeautificationCounter.numberOfOperations("azgi"));
  }
}
