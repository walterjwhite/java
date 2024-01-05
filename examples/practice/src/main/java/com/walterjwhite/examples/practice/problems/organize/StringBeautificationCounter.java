package com.walterjwhite.examples.practice.problems.organize;

public class StringBeautificationCounter {
  public int numberOfOperations(final String input) {
    int operations = 0;
    for (int i = 1; i < input.length(); i++) {
      final int diff = input.charAt(i) - input.charAt(i - 1);
      if (diff == 0 || diff == 1) {
        operations++;
        i++;
      }
    }

    return operations;
  }
}
