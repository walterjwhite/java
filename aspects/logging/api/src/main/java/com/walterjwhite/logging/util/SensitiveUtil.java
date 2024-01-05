package com.walterjwhite.logging.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SensitiveUtil {
  public static final int OUTPUT_LENGTH = 80;
  public static final int SENSITIVE_LAST_CHARACTERS_TO_DISPLAY = 4;

  public static String format(final String input, final boolean isSensitive) {
    if (!isSensitive) {
      return input;
    }

    if (input == null || input.length() == 0) {
      return input;
    }
    if (input.length() < SENSITIVE_LAST_CHARACTERS_TO_DISPLAY) {
      return "";
    }

    return input.substring(input.length() - SENSITIVE_LAST_CHARACTERS_TO_DISPLAY);
  }
}
