package com.walterjwhite.examples.practice.problems.organize.strings;

public class Compressor {
  private Compressor() {}

  public static String compress(final String input) {
    if (input == null) {
      return input;
    }

    if (input.length() == 1) {
      return input;
    }

    final StringBuilder compressed = new StringBuilder();
    for (int i = 0; i < input.length(); ) {
      final char c = input.charAt(i);
      int count = count(c, input.substring(i + 1));
      compressed.append(c);
      compressed.append(count);

      i += count;
    }

    if (compressed.length() < input.length()) {
      return compressed.toString();
    }

    return input;
  }

  private static int count(final char c, final String input) {
    int count = 1;
    for (int i = 0; i < input.length(); i++) {
      if (c != input.charAt(i)) {
        break;
      }

      count++;
    }

    return count;
  }
}
