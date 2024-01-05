package com.walterjwhite.examples.practice.problems;

public class ArrayShift {
  private ArrayShift() {}
  // copy and shift elements
  public static void shift(final int[] input, final int index, final int value) {
    if (input == null) {
      throw new IllegalArgumentException("Invalid input, null");
    }

    if (index < 0) {
      throw new IndexOutOfBoundsException(index);
    }

    // shift elements
    // truncate last element for simplicity
    //        int newValue = value;
    //        int oldValue = input[index];
    //        for(int i = index;i < input.length;i++){
    //            newValue = input[index];
    //
    //            input[i] = oldValue;
    //            oldValue =
    //        }
    //
    //        output[index] = value;
    //

  }
}
