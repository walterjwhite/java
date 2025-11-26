package com.walterjwhite.examples.practice.problems.google;

import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Google5 implements GoogleProblem {

  public int missing(int x[], int y[]) {
    final Set<Integer> both = new HashSet<>();
    final Set<Integer> xNotY = new HashSet<>();
    final Set<Integer> yNotX = new HashSet<>();

    for (int i = 0; i < x.length && i < y.length; i++) {
      if (x[i] == y[i]) {
        both.add(x[i]);

        xNotY.remove(x[i]);
        yNotX.remove(x[i]);
        continue;
      }

      if (yNotX.contains(x[i]) || both.contains(x[i])) {
        both.add(x[i]);
        xNotY.remove(x[i]);
        yNotX.remove(x[i]);
      } else {
        xNotY.add(x[i]);
      }

      if (xNotY.contains(y[i]) || both.contains(y[i])) {
        both.add(y[i]);
        yNotX.remove(y[i]);
        xNotY.remove(y[i]);
      } else {
        yNotX.add(y[i]);
      }

      LOGGER.info("i:{} both:{}, xNotY:{}, yNotX:{}", i, both, xNotY, yNotX);
    }

    if (!(xNotY.size() == 1 && yNotX.isEmpty()) && !(xNotY.isEmpty() && yNotX.size() == 1)) {
      remainder(x, y, both, xNotY, yNotX);
    }

    LOGGER.debug("result: both:{}, xNotY:{}, yNotX:{}", both, xNotY, yNotX);
    if (!xNotY.isEmpty()) {
      return xNotY.iterator().next();
    }

    return yNotX.iterator().next();
  }

  protected void remainder(
      final int[] x,
      final int[] y,
      final Set<Integer> both,
      final Set<Integer> xNotY,
      final Set<Integer> yNotX) {
    if (x.length > y.length) {

      if (yNotX.contains(x[x.length - 1])) {
        yNotX.remove(x[x.length - 1]);
        both.add(x[x.length - 1]);
      } else {
        if (!both.contains(x[x.length - 1])) {
          xNotY.add(x[x.length - 1]);
        }
      }
    } else {
      if (y.length > x.length) {
        if (xNotY.contains(y[y.length - 1])) {
          xNotY.remove(y[y.length - 1]);
          both.add(x[x.length - 1]);
        } else {
          if (!both.contains(y[y.length - 1])) {
            xNotY.add(y[y.length - 1]);
          }
        }
      }
    }
  }

  public static void main(final String[] args) {
    int[] x = new int[] {1, 2, 3, 4, 5};
  }
}
