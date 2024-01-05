package com.walterjwhite.logging.aspects;

import com.walterjwhite.logging.ContextualLoggerInstance;
import com.walterjwhite.logging.LoggerInstance;
import com.walterjwhite.logging.enumeration.LogLevel;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/** Logs the matching invocations at TRACE. */
@Aspect
public class TraceMethodLoggerAspect {
  // log all private invocations at trace level, exclude (this framework)
  @Around(
      "execution(private * *(..)) && !within(com.walterjwhite.logging..*) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) &&"
          + " !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doPrivateAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(private * *(..)) && !within(com.walterjwhite.logging..*) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) &&"
          + " @annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !within(*..*$AjcClosure*) && !execution(* *lambda$*(..))")
  public Object doContextualPrivateAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) && !execution(String"
          + " toString()) && !call(*.new(..)) && (execution(void set*(..)) || execution(!void"
          + " get*()) || execution(int hashCode(..)) || execution(boolean equals(..)) ||"
          + " execution(boolean is*())) && !within(*..*$AjcClosure*) && !execution(*"
          + " *lambda$*(..))")
  public Object doPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new LoggerInstance(point, LogLevel.TRACE).doAround());
  }

  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.logging..*) &&"
          + " @annotation(com.walterjwhite.logging.annotation.ContextualLoggable) &&"
          + " !@annotation(com.walterjwhite.logging.annotation.NonLoggable) && !call(*.new(..)) &&"
          + " !execution(String toString()) && (execution(void set*(..)) || execution(!void"
          + " get*()) || execution(int hashCode(..)) || execution(boolean equals(..)) ||"
          + " execution(boolean is*())) && !within(*..*$AjcClosure*) && !execution(*"
          + " *lambda$*(..))")
  public Object doContextualPublicAround(ProceedingJoinPoint point) throws Throwable {
    return (new ContextualLoggerInstance(point, LogLevel.TRACE).doAround());
  }
}
