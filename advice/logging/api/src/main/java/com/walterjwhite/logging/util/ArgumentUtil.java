package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.logging.enumeration.ArgumentType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentUtil {
  public static final int NUMBER_OF_ARGUMENTS_TO_LOG = 3;

  public static String toLoggableString(final int lastCharactersToDisplay, final Object argument) {
    return toLoggableString(lastCharactersToDisplay, argument, NUMBER_OF_ARGUMENTS_TO_LOG);
  }

  public static String toLoggableString(
      final int lastCharactersToDisplay, final Object argument, final int numberOfArguments) {
    if (argument == null) {
      return "null";
    }

    for (final ArgumentType argumentType : ArgumentType.values()) {
      if (argumentType.supports(argument.getClass())) {
        return argumentType.format(lastCharactersToDisplay, argument, numberOfArguments);
      }
    }

    throw new IllegalArgumentException(argument.getClass() + " is not supported");
  }

  public static String getArguments(final Sensitive[] sensitives, final Object[] arguments) {
    return getArguments(sensitives, arguments, NUMBER_OF_ARGUMENTS_TO_LOG);
  }

  public static String getArguments(
      final Sensitive[] sensitives, final Object[] arguments, int numberOfArguments) {
    if (arguments == null) {
      return "null";
    }

    if (arguments.length == 0) {
      return "[]";
    }

    int argumentLength = length(arguments.length, numberOfArguments);

    final String[] data = new String[argumentLength];
    for (int i = 0; i < argumentLength; i++) {
      data[i] = toLoggableString(getSensitiveValue(sensitives, i), arguments[i], numberOfArguments);
    }

    return ObjectFormatUtil.format(numberOfArguments, arguments.length, data);
  }

  public static int length(final int numberOfArguments, final int inputLength) {
    if (inputLength >= numberOfArguments) {
      return numberOfArguments;
    }

    return inputLength;
  }

  public static int getSensitiveValue(final Sensitive[] sensitives, final int index) {
    if (sensitives == null) {
      return -1;
    }

    if (sensitives[index] == null) {
      return -1;
    }

    return sensitives[index].value();
  }
}
