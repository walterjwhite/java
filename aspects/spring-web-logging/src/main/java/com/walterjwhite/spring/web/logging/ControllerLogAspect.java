package com.walterjwhite.spring.web.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {
  private static final ThreadLocal<String> INVOCATION_THREAD_LOCAL = new ThreadLocal<>();

  @Pointcut(
      "within(@org.springframework.web.bind.annotation.RestController *) ||"
          + " (within(@Log *) && execution(* *(..)))")
  public void log() {}

  @Before(value = "log()")
  public void beforeInvocation(final JoinPoint joinpoint) {
    INVOCATION_THREAD_LOCAL.set(
        String.format("%s - %s", JoinPointUtil.getIdentifier(joinpoint), "request id"));

    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("{} - Before", INVOCATION_THREAD_LOCAL.get());
    }
  }

  @After(value = "log()")
  public void afterInvocation(final JoinPoint joinpoint) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("{} - After", INVOCATION_THREAD_LOCAL.get());
    }

    INVOCATION_THREAD_LOCAL.remove();
  }

  @AfterThrowing(value = "log()", throwing = "e")
  public void afterThrowing(final JoinPoint joinpoint, final Throwable e) {
    LOGGER.error(String.format("%s - ERROR (%s)", INVOCATION_THREAD_LOCAL.get()), e);

    INVOCATION_THREAD_LOCAL.remove();
  }
}
