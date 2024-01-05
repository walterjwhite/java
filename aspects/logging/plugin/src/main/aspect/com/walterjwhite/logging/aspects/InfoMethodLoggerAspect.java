package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.ContextualLoggerInstance;
import com.walterjwhite.logging.LoggerInstance;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/** Logs the matching invocations at INFO. */
@Aspect
public class InfoMethodLoggerAspect {
  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) && !execution(void"
          + " set*(..)) && !execution(!void get*()) && !execution(int hashCode(..)) &&"
          + " !execution(boolean equals(..)) && !execution(boolean is*()) && !execution(String"
          + " toString()) && !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.INFO).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) && !call(*.new(..)) &&"
          + " @annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) && !execution(void"
          + " set*(..)) && !execution(!void get*()) && !execution(int hashCode(..)) &&"
          + " !execution(boolean equals(..)) && !execution(boolean is*()) && !execution(String"
          + " toString()) && !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doContextualPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.INFO).doAround());
  }
}
