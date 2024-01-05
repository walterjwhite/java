package com.walterjwhite.examples.practice.problems.organize.leetcode;

import java.util.Arrays;
import lombok.Getter;

public class RomanToIntWithEnum implements RomanToInt {
  public int convert(final String input) {
    int value = 0;
    for (int i = 0; i < input.length(); i++) {
      final RomanNumeral current = RomanNumeral.valueOf(String.valueOf(input.charAt(i)));
      value += getOperation(input, i, current) * current.getValue();
    }

    return value;
  }

  private static int getOperation(final String input, final int i, final RomanNumeral current) {
    if (i + 1 >= input.length()) {
      return 1;
    }

    if (current.isSubtract(String.valueOf(input.charAt(i + 1)))) {
      return -1;
    }

    return 1;
  }

  @Getter
  enum RomanNumeral {
    M(1000),
    D(500),
    L(50),
    V(5),
    C(100, D, M),
    X(10, L, C),
    I(1, V, X);

    private final int value;
    private final RomanNumeral[] subtractWhen;

    RomanNumeral(int value, RomanNumeral... subtractWhen) {
      this.value = value;
      this.subtractWhen = subtractWhen;
    }

    public boolean isSubtract(final String next) {
      if (subtractWhen == null) {
        return false;
      }

      if (next == null) {
        return false;
      }

      final RomanNumeral nextRomanNumeral = valueOf(next);
      return Arrays.stream(subtractWhen).anyMatch(s -> s.equals(nextRomanNumeral));
    }
  }
}
