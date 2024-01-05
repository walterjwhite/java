package com.walterjwhite.examples.practice.problems.broadcom;

import java.math.BigInteger;

/**
 * Write a function that takes a positive integer, N, and returns the maximal product of a set of
 * positive integers whose sum is N. Invalid inputs should return 0, that is non-positive integers
 * or any integer where no set of at least 2 positive addends exists.
 *
 * <p>For example: Given 8, the result is 18. 8 can be written as 2*2*2*2 whose product is 16.
 * However, it can also be written as 3*3*2 whose product is 18. 9 -> 3*3, 6 = 0
 */
public interface BroadcomSum {
  default int maxProductInt(final int n) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  default long maxProductLong(final int n) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  default BigInteger maxProductBigInteger(final int n) {
    throw new UnsupportedOperationException("Not yet implemented");
  }
}
