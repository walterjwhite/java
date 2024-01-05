package com.walterjwhite.examples.practice.problems.organize.palindrome;

public class PalindromeB implements Palindrome {
  /**
   * Alternate attempt, not even close, I need to use dynamic programming? start @ 0 start @ end
   * decrement end until we find a character matching the start then check all of the characters in
   * between
   *
   * @param input
   * @return
   */
  @Override
  public String longestPalindrome(String input) {
    if (input.length() == 1) {
      return input;
    }

    int start = 0;
    int length = 1;

    for (int i = 0; i < input.length(); i++) {
      for (int j = input.length() - 1; j >= i + length; j--) {
        if (input.charAt(i) == input.charAt(j)) {
          int newLength = 1 + j - i;
          if (newLength > length) {
            // possible palindrome
            if (j - i > 1) {
              // check in between
              // even (a, b, b, a)
              // odd (1, b, 1)
            } else {
              length = newLength;
              start = i;
              break;
            }
          }
        }
      }
    }

    return input.substring(start, start + length);
  }
}
