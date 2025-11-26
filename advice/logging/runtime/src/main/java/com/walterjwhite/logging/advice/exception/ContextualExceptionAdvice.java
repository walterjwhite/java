package com.walterjwhite.logging.advice.exception;

import com.walterjwhite.logging.ContextualLoggable;
import com.walterjwhite.logging.util.ExceptionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextualExceptionAdvice {
  @Advice.OnMethodExit(onThrowable = Throwable.class)
  public static void onException(
      @Advice.This final Object intercepted,
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.AllArguments Object[] arguments,
      @Advice.Thrown Throwable throwable) {
    if (throwable == null) {
      return;
    }

    ExceptionUtil.onException(
        (ContextualLoggable) intercepted, typeName, methodName, arguments, throwable);
  }
}
