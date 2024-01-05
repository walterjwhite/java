package com.walterjwhite.logging.util;

import com.walterjwhite.logging.enumeration.LogLevel;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MethodLogUtil {
  public static long onEnter(
      final LogLevel logLevel, final String typeName, final String methodName, Object[] arguments) {

    logLevel.log(
        LoggerFactory.getLogger(typeName),
        "{}({})",
        methodName,
        ArgumentUtil.getArguments(false, arguments));
    return System.nanoTime();
  }

  public static void onExit(
      final LogLevel logLevel,
      final String typeName,
      final String methodName,
      Object[] arguments,
      Object result,
      final long startTime) {
    logLevel.log(
        LoggerFactory.getLogger(typeName),
        "{}({}) => {} in {}ns",
        methodName,
        ArgumentUtil.getArguments(false, arguments),
        ArgumentUtil.getArgument(false, result),
        (System.nanoTime() - startTime));
  }
}
