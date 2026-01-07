package com.walterjwhite.metrics.advice;

import com.walterjwhite.metrics.api.instance.TimerInstance;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class TimerAspect {
  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.metrics..*) && !call(*.new(..)) &&"
          + " !within(@NoMetrics *) && !execution(void set*(..)) && !execution(!void get*()) &&"
          + " !execution(int hashCode(..)) && !execution(boolean equals(..)) && !execution(boolean"
          + " is*()) && !execution(String toString()) && !within(*..*$AjcClosure*) && !execution(*"
          + " *lambda$*(..))")
  public Object doTimer(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    return new TimerInstance(proceedingJoinPoint).doAround();
  }
}
