package com.walterjwhite.examples.practice.problems.organize.leetcode;

public class RomanToIntCase2 implements RomanToInt {
  @Override
  public int convert(String input) {
    int value = 0;
    for (int i = 0; i < input.length(); i++) {
      final char c = input.charAt(i);
      switch (c) {
        case 'M':
          value += 1000;
          break;
        case 'D':
          value += 500;
          break;
        case 'L':
          value += 50;
          break;
        case 'V':
          value += 5;
          break;
        default:
          switch (c) {
            case 'C':
              value += add(100, next(i + 1, input), 'D', 'M');
              break;
            case 'X':
              value += add(10, next(i + 1, input), 'L', 'C');
              break;
            case 'I':
              value += add(1, next(i + 1, input), 'V', 'X');
              break;
            default:
              throw new IllegalArgumentException(c + " was not understood");
          }
      }
    }
    return value;
  }

  private static int add(final int value, final char next, final char... subtractChars) {
    if (next == 0) {
      return value;
    }

    for (char subtractChar : subtractChars) {
      if (subtractChar == next) {
        return -value;
      }
    }

    return value;
  }

  private static char next(final int index, final String input) {
    if (index >= input.length()) {
      return 0;
    }

    return input.charAt(index);
  }

  private static int valueOf(final char c) {
    switch (c) {
      case 'M':
        return 1000;
      case 'D':
        return 500;
      case 'L':
        return 50;
      case 'V':
        return 5;
      case 'C':
        return 100;
      case 'X':
        return 10;
      case 'I':
        return 1;
      default:
        throw new IllegalArgumentException(c + " was not understood");
    }
  }
}
