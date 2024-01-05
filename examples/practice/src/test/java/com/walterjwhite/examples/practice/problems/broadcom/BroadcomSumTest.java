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
    // 1: 1  =0
    // 2: 2  =0
    // 3: 2*1=2
    // 4: 2*2=4
    // 5: 3*2=6
    // 6: 3*3=9
    // 7: 3*3*1=9
    // 8: 3*3*2=18
    // 9: 3*3*3=27
    // 10:3*3*3*1=27
    // 11:3*3*3*2=54
    // 12:3*3*3*3=81
    // 13:3*3*3*3*1=81
    // 14:3*3*3*3*2=162
    // 15:3*3*3*3*3=243
    // 16:3*3*3*3*3*1=243
    // 17:3*3*3*3*3*2=486
    // 18:3*3*3*3*3*3=729
    // 19:3*3*3*3*3*3*1=729
    // 20:3*3*3*3*3*3*2=1458,2*3^6
    // 21:3*3*3*3*3*3*3=2187,3^7
    // 22:3*3*3*3*3*3*3*1=2187,3^7
    // 23:3*3*3*3*3*3*3*2=4374,2*3^7
    // 24:3*3*3*3*3*3*3*3=6561, 3^8
    // 100:4^25=1.12589990684e+15,3^33=5.55906056656e+15,2^50=1.12589990684e+15
    // 119:2*3^39
    // 120:outside bounds of long
    // 122:3^40
    // 123:3^41
    // 124:3^41
    // 125:2*3^41
    for (int i = 0; i < inputs.length; i++) {
      Assertions.assertEquals(
          outputs[i],
          broadcomSum.maxProductLong(inputs[i]),
          String.format("Input: %d=>%d", i, inputs[i]));
    }
  }
}
