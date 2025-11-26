package com.walterjwhite.examples.practice.problems.organize.strings;

import java.util.HashSet;
import java.util.Set;

public class IsPermutation {
  private IsPermutation() {}

  public static boolean IsPermutation(final String a, final String b) {
    if (a == null) {
      return false;
    }

    if (b == null) {
      return false;
    }

    if (a.length() != b.length()) {
      return false;
    }

    final Set<Integer> matchedIndexes = new HashSet<>();
    for (int i = 0; i < b.length(); i++) {
      for (int j = 0; j < a.length(); j++) {
        if (matchedIndexes.contains(j)) {
          continue;
        }

        if (a.charAt(j) == b.charAt(i)) {
          matchedIndexes.add(j);
          break;
        }
      }
    }

    return matchedIndexes.size() == a.length();
  }
}
