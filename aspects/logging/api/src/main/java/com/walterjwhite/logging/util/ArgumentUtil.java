package com.walterjwhite.logging.util;

import com.walterjwhite.logging.enumeration.ArgumentType;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentUtil {
  public static final int NUMBER_OF_ARGUMENTS_TO_LOG = 3;

  // *SHOULD* be fixed in newer versions of hibernate
  public static String[] AVOID_TO_STRING_CLASSES =
      new String[] {"javax.persistence.EntityManager", "org.hibernate.internal.SessionImpl"};

  public static Object getArgument(final boolean isSensitive, Object argument) {
    return getArgument(isSensitive, argument, NUMBER_OF_ARGUMENTS_TO_LOG);
  }

  public static Object getArgument(
      final boolean isSensitive, Object argument, final int numberOfArguments) {
    if (argument == null) {
      return "null";
    }

    for (final ArgumentType argumentType : ArgumentType.values()) {
      if (argumentType.supports(argument.getClass())) {
        return argumentType.format(isSensitive, argument, numberOfArguments);
      }
    }

    throw new IllegalArgumentException(argument.getClass() + " is not supported");
  }

  public static Object[] getArguments(final boolean isSensitive, final Object[] arguments) {
    return getArguments(isSensitive, arguments, NUMBER_OF_ARGUMENTS_TO_LOG);
  }

  public static Object[] getArguments(
      final boolean isSensitive, final Object[] arguments, final Object[] contextual) {
    return getArguments(isSensitive, arguments, contextual, NUMBER_OF_ARGUMENTS_TO_LOG);
  }

  public static Object[] getArguments(
      final boolean isSensitive,
      final Object[] arguments,
      final Object[] contextual,
      int numberOfArguments) {
    int argumentLength = getArgumentLength(arguments, numberOfArguments);
    final Object[] data = Arrays.copyOf(arguments, argumentLength + contextual.length);
    for (int i = 0; i < argumentLength; i++)
      data[i] = getArgument(isSensitive, arguments[i], numberOfArguments);

    for (int i = 0; i < contextual.length; i++) {
      data[argumentLength + i] = contextual[i];
    }

    return (data);
  }

  public static Object[] getArguments(
      final boolean isSensitive, final Object[] arguments, int numberOfArguments) {
    if (arguments == null || arguments.length == 0) {
      return null;
    }

    int argumentLength = getArgumentLength(arguments, numberOfArguments);

    final Object[] data = new Object[argumentLength];
    for (int i = 0; i < argumentLength; i++) {
      data[i] = getArgument(isSensitive, arguments[i], numberOfArguments);
    }

    return (data);
  }

  public static int getArgumentLength(final Object[] arguments, int numberOfArguments) {
    if (arguments == null || arguments.length == 0) {
      return 0;
    }

    if (arguments.length > numberOfArguments) {
      return numberOfArguments;
    }

    return arguments.length;
  }

  public static String[] trim(final String[] in, final int lastIndex, final int numberOfArguments) {
    if (lastIndex < numberOfArguments - 1) {
      return Arrays.copyOf(in, lastIndex + 1);
    }

    return in;
  }

  public static Object formatOutput(final String[] arguments, final boolean hasMore) {
    if (hasMore) {
      return "first " + arguments.length + "(" + String.join(",", arguments) + ")";
    }

    if (arguments.length > 1) {
      return "(" + String.join(",", arguments) + ")";
    }

    if (arguments.length == 1) {
      return arguments[0];
    }

    return "VOID";
  }

  public static String format(final Object element, final boolean isSensitive) {
    if (element == null) {
      return "null";
    }

    if (Arrays.binarySearch(ArgumentUtil.AVOID_TO_STRING_CLASSES, element.getClass().getName())
        == -1) {
      return SensitiveUtil.format(element.toString(), isSensitive);
    }

    return element.getClass() + "@" + element.hashCode();
  }
}
