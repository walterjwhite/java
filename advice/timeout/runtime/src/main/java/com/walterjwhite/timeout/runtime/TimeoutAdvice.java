package com.walterjwhite.timeout.runtime;

import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.runtime.TimeConstrainedMethodCall;
import java.time.Duration;
import net.bytebuddy.asm.Advice;

public class TimeoutAdvice {

  @Advice.OnMethodEnter
  public static TimeConstrainedMethodCall onEnter(@Advice.This Object intercepted) {
    final TimeConstrainedMethodInvocation timeConstrainedMethodInvocation =
        (TimeConstrainedMethodInvocation) intercepted;

    if (timeConstrainedMethodInvocation.getAllowedExecutionDuration() == null) {
      return null;
    }

    final Duration duration = timeConstrainedMethodInvocation.getAllowedExecutionDuration();
    return new TimeConstrainedMethodCall(duration);
  }

  @Advice.OnMethodExit
  public static void onExit(
      @Advice.Enter final TimeConstrainedMethodCall timeConstrainedMethodCall) {
    if (timeConstrainedMethodCall != null) {
      timeConstrainedMethodCall.cancelInterruption();
    }
  }
}
