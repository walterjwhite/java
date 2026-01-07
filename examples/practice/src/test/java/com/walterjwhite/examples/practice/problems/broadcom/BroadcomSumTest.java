package com.walterjwhite.examples.practice.problems.broadcom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BroadcomSumTest {
  protected final BroadcomSum broadcomSum = new BroadcomSum1();

  protected final int[] inputs =
      new int[] {
        -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
        24, 100, 119, 120, 124, 150, 175, 199
      };
  protected final long[] outputs =
      new long[] {
        0,
        0,
        0,
        0,
        2,
        4,
        6,
        9,
        9,
        18,
        27,
        27,
        54,
        81,
        81,
        162,
        243,
        243,
        486,
        729,
        729,
        1458,
        2187,
        2187,
        4374,
        6561,
        5559060566555523l,
        8105110306037952512l,
        0,
        0,
        0,
        0,
        0,
        0
      };

  @Test
  void sum() {
    for (int i = 0; i < inputs.length; i++) {
      Assertions.assertEquals(
          outputs[i],
          broadcomSum.maxProductLong(inputs[i]),
          String.format("Input: %d=>%d", i, inputs[i]));
    }
  }
}
