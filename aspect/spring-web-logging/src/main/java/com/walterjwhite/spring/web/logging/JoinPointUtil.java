package com.walterjwhite.spring.web.logging;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;

public interface JoinPointUtil {
  static String getClassName(final JoinPoint joinPoint) {
    return joinPoint.getSignature().getDeclaringTypeName();
  }

  static String getMethodName(final JoinPoint joinPoint) {
    return joinPoint.getSignature().getName();
  }

  static String getArguments(final JoinPoint joinPoint) {
    return Arrays.toString(joinPoint.getArgs());
  }

  static String getIdentifier(final JoinPoint joinPoint) {
    return String.format(
        "%s.%s(%s)", getClassName(joinPoint), getMethodName(joinPoint), getArguments(joinPoint));
  }
}
