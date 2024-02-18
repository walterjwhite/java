package com.walterjwhite.transform.rot13_5;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ROT13_5Util {
  public static String transform(final String input) {
    final char[] buffer = new char[input.length()];
    for (int i = 0; i < input.length(); i++) {
      buffer[i] = transform(input.charAt(i));
    }

    return new String(buffer);
  }

  private static char transform(char input) {
    if ((input >= 'a' && input <= 'm') || input >= 'A' && input <= 'M') {
      input += 13;
    } else if ((input >= 'n' && input <= 'z') || input >= 'N' && input <= 'Z') {
      input -= 13;
    } else if (input >= '0' && input <= '4') {
      input += 5;
    } else if (input >= '5' && input <= '9') {
      input -= 5;
    }

    return input;
  }
}
