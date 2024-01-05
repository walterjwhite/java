package com.walterjwhite.examples.practice.problems.organize.fibonacci;

public class Fibonacci2 {

  public void print(final int n) {
    for (int i = 0; i <= n; i++) {}
  }

  private int get(final int n) {
    if (n < 0) {
      throw new IllegalArgumentException("Invalid input: " + n);
    }

    if (n < 2) {
      return 1;
    }

    return 0;
  }
}
