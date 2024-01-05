package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.StackTraceLoggerInstance;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/** Log the stack trace for an exception */
@Aspect
public class ExceptionStackTraceLoggerAspect {
  @Before(
      "execution(* *(..)) && !within(com.walterjwhite.logging..*) &&"
          + " @annotation(com.walterjwhite.logging.annotation.LogStackTrace)")
  public void logExceptionStackTrace(JoinPoint joinPoint) throws Throwable {
    new StackTraceLoggerInstance(joinPoint).logException();
  }
}
