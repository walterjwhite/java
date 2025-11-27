package com.walterjwhite.examples.practice.leetcode;

import com.walterjwhite.examples.practice.problems.organize.leetcode.RomanToInt;
import com.walterjwhite.examples.practice.problems.organize.leetcode.RomanToIntCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomanToIntTest {
  protected final RomanToInt romanToInt = new /*RomanToIntWithEnum*/ RomanToIntCase();


  @Test
  void testIII() {
    Assertions.assertEquals(3, romanToInt.convert("III"));
  }

  @Test
  void testIV() {
    Assertions.assertEquals(4, romanToInt.convert("IV"));
  }

  @Test
  void testIIV() {
    Assertions.assertEquals(5, romanToInt.convert("IIV"));
  }

  @Test
  void testLVIII() {
    Assertions.assertEquals(58, romanToInt.convert("LVIII"));
  }

  @Test
  void testMCMXCIV() {
    Assertions.assertEquals(1994, romanToInt.convert("MCMXCIV"));
  }
}
