package com.walterjwhite.examples.practice.problems;

public class ArrayShift {
  private ArrayShift() {}

  public static void shift(final int[] input, final int index, final int value) {
    if (input == null) {
      throw new IllegalArgumentException("Invalid input, null");
    }

    if (index < 0) {
      throw new IndexOutOfBoundsException(index);
    }


  }
}
