package com.walterjwhite.examples.practice.problems.organize.strings;

import java.util.HashSet;
import java.util.Set;

public class ContainsAllUniqueCharacters {
  private ContainsAllUniqueCharacters() {}

  public static boolean containsAllUniqueCharacters(final String input) {
    if (input == null) {
      return true;
    }

    if (input.length() == 1) {
      return true;
    }


    return noStore(input);
  }

  private static boolean store(final String input) {
    final Set<Character> uniqueChars = new HashSet<>();
    for (int i = 0; i < input.length(); i++) {
      uniqueChars.add(input.charAt(i));
    }

    return uniqueChars.size() == input.length();
  }

  private static boolean noStore(final String input) {
    for (int i = 0; i < input.length() - 1; i++) {
      if (!noStore(input.charAt(i), input.substring(i + 1))) {
        return false;
      }
    }

    return true;
  }

  private static boolean noStore(final char c, final String input) {
    return input.chars().noneMatch(cc -> c == cc);
  }
}
