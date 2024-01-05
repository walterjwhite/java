package com.walterjwhite.examples.practice.leetcode;

import com.walterjwhite.examples.practice.problems.organize.leetcode.RomanToInt;
import com.walterjwhite.examples.practice.problems.organize.leetcode.RomanToIntCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RomanToIntTest {
  protected final RomanToInt romanToInt = new /*RomanToIntWithEnum*/ RomanToIntCase();

  //    I             1
  //    V             5
  //    X             10
  //    L             50
  //    C             100
  //    D             500
  //    M             1000
  // I can be placed before V (5) and X (10) to make 4 and 9.
  //    X can be placed before L (50) and C (100) to make 40 and 90.
  //    C can be placed before D (500) and M (1000) to make 400 and 900.

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
  // 1000 + 900 + 90 + 4
  @Test
  void testMCMXCIV() {
    Assertions.assertEquals(1994, romanToInt.convert("MCMXCIV"));
  }
}
