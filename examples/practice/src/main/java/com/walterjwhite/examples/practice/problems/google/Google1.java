package com.walterjwhite.examples.practice.problems.google;

import java.util.HashSet;
import java.util.Set;

public class Google1 implements GoogleProblem {
  public int missing(int x[], int y[]) {
    final Set<Integer> other = init(y);
    for (int i = 0; i < x.length; i++) {
      if (!other.remove(x[i])) {
        return x[i];
      }
    }

    return other.iterator().next();
  }

  private static Set<Integer> init(final int[] input) {
    final Set<Integer> output = new HashSet<>();
    for (int i = 0; i < input.length; i++) {
      output.add(input[i]);
    }

    return output;
  }
}
