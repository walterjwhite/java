package com.walterjwhite.metrics.advice;

import com.walterjwhite.metrics.api.instance.GaugeInstance;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class GaugeAspect {
  @Around(
      "execution(public * *(..)) && !within(com.walterjwhite.metrics..*) && !call(*.new(..)) &&"
          + " !within(@NoMetrics *) && !execution(void set*(..)) && !execution(!void get*()) &&"
          + " !execution(int hashCode(..)) && !execution(boolean equals(..)) && !execution(boolean"
          + " is*()) && !execution(String toString()) && !within(*..*$AjcClosure*) && !execution(*"
          + " *lambda$*(..))")
  public Object doGauge(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    return new GaugeInstance(proceedingJoinPoint).doAround();
  }
}
