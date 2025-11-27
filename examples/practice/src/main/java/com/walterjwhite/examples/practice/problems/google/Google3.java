package com.walterjwhite.examples.practice.problems.google;

import java.util.Arrays;

public class Google3 implements GoogleProblem {

  public int missing(int x[], int y[]) {

    int missing = Arrays.stream(x).reduce(0, (a, b) -> a + b);
    return missing;
  }

  public static void main(final String[] args) {
    int[] x = new int[] {1, 2, 3, 4, 5};
  }
}
