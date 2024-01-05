package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.google.Google4;
import com.walterjwhite.examples.practice.problems.google.GoogleProblem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoogleTest {
  protected final GoogleProblem googleProblem = new Google4();

  @Test
  void test_1() {
    Assertions.assertEquals(
        6, googleProblem.missing(new int[] {13, 5, 6, 2, 5}, new int[] {5, 2, 5, 13}));
  }

  @Test
  void test_2() {
    Assertions.assertEquals(
        -4,
        googleProblem.missing(
            new int[] {14, 27, 1, 4, 2, 50, 3, 1}, new int[] {2, 4, -4, 3, 1, 1, 14, 27, 50}));
  }

  @Test
  void test_3() {
    Assertions.assertEquals(
        -4,
        googleProblem.missing(
            new int[] {14, 27, 1, 4, 2, 50, 3, 1, 50}, new int[] {2, 4, -4, 3, 1, 1, 14, 27, 50}));
  }
}
