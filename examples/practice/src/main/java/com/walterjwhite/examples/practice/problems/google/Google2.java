package com.walterjwhite.examples.practice.problems.google;

import java.util.Set;

public class Google2 implements GoogleProblem {
  public int missing(int x[], int y[]) {
    final Set<Integer> other = null; // init(y);
    for (int i = 0; i < x.length; i++) {
      if (!other.remove(x[i])) {
        return x[i];
      }
    }
    return other.iterator().next();
  }
}
