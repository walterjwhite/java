package com.walterjwhite.examples.practice.problems.broadcom;

public class BroadcomSum1 implements BroadcomSum {
  @Override
  public long maxProductLong(final int n) {
    if (n <= 2) {
      return 0;
    }

    // will exceed long
    if (n > 119) {
      return 0;
    }

    if (n == 3) {
      return 2;
    }
    if (n == 4) {
      return 4;
    }

    int threes = n / 3;
    final int remainder = n - 3 * threes;

    long product = (long) Math.pow(3, threes);
    if (remainder == 2) {
      // validate
      if (threes * 3 + 2 != n) {
        throw new IllegalStateException(
            "Poorly written algorithm, failed: " + threes + "*3+ 2 != " + n);
      }

      return product * 2;
    }

    return product;
  }
}
