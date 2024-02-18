package com.walterjwhite.logging.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SensitiveUtil {

  public static String format(final String input, final int lastCharactersToDisplay) {
    if (lastCharactersToDisplay < 0) {
      return input;
    }

    if (input == null || input.length() == 0) {
      return input;
    }
    if (input.length() < lastCharactersToDisplay) {
      return "";
    }

    return input.substring(input.length() - lastCharactersToDisplay);
  }
}
