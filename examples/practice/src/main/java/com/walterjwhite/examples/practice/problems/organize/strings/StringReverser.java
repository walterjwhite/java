package com.walterjwhite.examples.practice.problems.organize.strings;

public class StringReverser {
  private StringReverser() {}
  //    public static void main(final String[] arguments){
  //    }

  // efficiency O(n), iterates through each element
  public static String reverse(final String input) {
    if (input == null || input.length() == 1) {
      return input;
    }

    return input.charAt(input.length() - 1) + reverse(input.substring(0, input.length() - 1));
  }
}
