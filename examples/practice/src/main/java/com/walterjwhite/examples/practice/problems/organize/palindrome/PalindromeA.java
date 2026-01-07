package com.walterjwhite.examples.practice.problems.organize.palindrome;

public class PalindromeA implements Palindrome {



  public String longestPalindrome(String s) {
    if (s == null) {
      return null;
    }

    if (s.length() == 1) {
      return s;
    }

    int start = 0;
    int length = 1;

    for (int i = 0; i < s.length(); i++) {
      if (!canMakeLonger(s, length, i)) {
        break;
      }

      int next = i;
      while (true) {
        next = next(s, i, next + 1);
        if (next < 0) {
          break;
        }

        if (next - i < length) {
          continue;
        }

        if (is(s, i, next)) {
          start = i;
          length = 1 + next - start;
        }
      }
    }

    return s.substring(start, start + length);
  }

  private boolean canMakeLonger(final String s, final int length, final int i) {
    return (length < s.length() - i);
  }

  private int next(final String input, final int c, final int start) {
    for (int i = start; i < input.length(); i++) {
      if (input.charAt(i) == input.charAt(c)) {
        return i;
      }
    }

    return -1;
  }

  private boolean is(final String input, int start, int end) {
    while (end > start) {
      if (input.charAt(start) != input.charAt(end)) {
        return false;
      }

      start++;
      end--;
    }

    return true;
  }
}
