package com.walterjwhite.examples.practice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HourMinuteHandAngleTest {
  @Test
  void test0_0() {
    Assertions.assertEquals(0, HourMinuteHandAngle.computeAngle(0, 0));
  }

  @Test
  void test0_5() {
    Assertions.assertEquals(30, HourMinuteHandAngle.computeAngle(0, 5));
  }

  @Test
  void test11_5() {
    final int angle = HourMinuteHandAngle.computeAngle(11, 5);
    Assertions.assertTrue(angle == 60 || angle == 300);
  }
}
