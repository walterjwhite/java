package com.walterjwhite.examples.practice.problems.google;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Google4 implements GoogleProblem {

  public int missing(int[] x, int[] y) {
    for (int i = 0; i < x.length; i++) {
      final int value = missing(x, y, x[0] /*, 0, 0*/);
      if (value != Integer.MIN_VALUE) {
        return value;
      }
    }

    throw new IllegalStateException("Not found");
  }

  protected int missing(int[] x, int[] y, final int lookup) {

    return -1;
  }
}
