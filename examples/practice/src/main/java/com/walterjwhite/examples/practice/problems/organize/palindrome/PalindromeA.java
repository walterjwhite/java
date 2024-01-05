package com.walterjwhite.examples.practice.problems.organize.palindrome;

public class PalindromeA implements Palindrome {
  // a palindrome is a word that is the same if read forward or backward
  // shortcuts, first and last letter must match
  // typing is easier for me, I don't talk and type

  // start at the first letter
  // then search the string for the next occurrence of that letter
  // if it matches, that is the next occurrence
  // if it doesn't match, then

  // babad
  // b -> a, no, previous is the start, acceptable
  // b -> b, yes, valid, length = 3
  // b -> a, no, invalid
  // b -> d, no, invalid
  // a -> b, no, previous is the start, acceptable
  // a -> a, yes, length = 3
  // a -> d, no
  // b -> a, no, previous is the start, acceptable
  // a -> d, no
  // then work backwords to confirm it is a palindrome, if so, then store it
  // additionally, we can also try going forward to see if we can make a longer palindrome
  // keep track of index and length

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

        // if the length of the possible palindrome is less than (or equal) the max length already,
        // we don't need to validate because it won't be any longer
        if (next - i < length) {
          continue;
        }

        // if it is a palindrome, then update the #'s
        if (is(s, i, next)) {
          start = i;
          length = 1 + next - start;
        }
      }
    }

    return s.substring(start, start + length);
  }

  // check if we can exit early
  // if the max length is already longer than we have left in the string, then we can exit
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
