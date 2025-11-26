package com.walterjwhite.logging.util;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ObjectFormatUtil {
  public static String[] AVOID_TO_STRING_CLASSES =
      new String[] {"javax.persistence.EntityManager", "org.hibernate.internal.SessionImpl"};

  public static String format(final Object element, final int lastCharactersToDisplay) {
    if (element == null) {
      return "null";
    }

    if (Arrays.binarySearch(AVOID_TO_STRING_CLASSES, element.getClass().getName()) == -1) {
      return SensitiveUtil.format(element.toString(), lastCharactersToDisplay);
    }

    return element.getClass() + "@" + element.hashCode();
  }

  public static String format(
      final int argumentLength, final int inputLength, final String[] elements) {
    return getPrefix(argumentLength, inputLength) + "[" + String.join(", ", elements) + "]";
  }

  private static String getPrefix(final int argumentLength, final int inputLength) {
    if (argumentLength < inputLength) {
      return "first " + argumentLength + " of " + inputLength + " ";
    }

    return "";
  }
}
