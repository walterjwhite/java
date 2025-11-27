package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.logging.enumeration.LogLevel;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodLogUtil {
  public static long onEnter(
      final LogLevel logLevel,
      final String typeName,
      final String methodName,
      final Sensitive[] sensitives,
      final Object[] arguments) {
    logLevel.log(
        LoggerFactory.getLogger(typeName),
        "{}({})",
        methodName,
        ArgumentUtil.getArguments(sensitives, arguments));
    return System.nanoTime();
  }

  public static void onExit(
      final LogLevel logLevel,
      final String typeName,
      final String methodName,
      final Sensitive[] sensitives,
      Object[] arguments,
      Object result,
      final long startTime,
      final int lastCharactersToDisplay) {
    logLevel.log(
        LoggerFactory.getLogger(typeName),
        "{}({}) => {} in {}ns",
        methodName,
        ArgumentUtil.getArguments(sensitives, arguments),
        ArgumentUtil.toLoggableString(lastCharactersToDisplay, result),
        (System.nanoTime() - startTime));
  }

  public static Sensitive[] getSensitives(final Executable executable) {
    final Sensitive[] sensitives = new Sensitive[executable.getParameters().length];
    int i = 0;
    for (final Parameter parameter : executable.getParameters()) {
      for (final Annotation annotation : parameter.getAnnotations()) {
        sensitives[i++] = parameter.getDeclaredAnnotation(Sensitive.class);
      }
    }

    return sensitives;
  }

  public static Sensitive[] getSensitives(final Method method) {
    final Sensitive[] sensitives = new Sensitive[method.getParameterCount()];
    for (int i = 0; i < method.getParameterCount(); i++) {
      sensitives[i] = method.getParameters()[i].getDeclaredAnnotation(Sensitive.class);
    }

    return sensitives;
  }
}
