package com.walterjwhite.examples.practice.problems.organize.strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZeroerTest {
  @Test
  void testNull() {
    final int[][] input = null;
    Zeroer.zero(input);

    Assertions.assertArrayEquals(null, input);
  }

  @Test
  void test1x1_1() {
    final int[][] input = new int[1][1];
    input[0][0] = 1;
    Zeroer.zero(input);

    final int[][] expected = new int[1][1];
    expected[0][0] = 1;

    Assertions.assertArrayEquals(expected, input);
  }

  @Test
  void test1x1_0() {
    final int[][] input = new int[1][1];
    input[0][0] = 0;
    Zeroer.zero(input);

    final int[][] expected = new int[1][1];
    expected[0][0] = 0;

    Assertions.assertArrayEquals(expected, input);
  }

  @Test
  void test2x2_1() {
    final int[][] input = new int[2][2];
    input[0][0] = 1;
    input[0][1] = 1;
    input[1][0] = 1;
    input[1][1] = 1;
    Zeroer.zero(input);

    final int[][] expected = new int[2][2];
    expected[0][0] = 1;
    expected[0][1] = 1;
    expected[1][0] = 1;
    expected[1][1] = 1;

    Assertions.assertArrayEquals(expected, input);
  }

  @Test
  void test2x2_0() {
    final int[][] input = new int[2][2];
    input[0][0] = 1;
    input[0][1] = 0;
    input[1][0] = 1;
    input[1][1] = 1;
    Zeroer.zero(input);

    final int[][] expected = new int[2][2];
    expected[0][0] = 0;
    expected[0][1] = 0;
    expected[1][0] = 1;
    expected[1][1] = 0;

    Assertions.assertArrayEquals(expected, input);
  }
}
