package com.walterjwhite.examples.practice.problems.organize.palindrome;

public class PalindromeB implements Palindrome {
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
            if (j - i > 1) {
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
