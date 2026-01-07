package com.walterjwhite.logging.advice.exception;

import com.walterjwhite.logging.util.ExceptionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionAdvice {
  @Advice.OnMethodExit(onThrowable = Throwable.class)
  public static void onException(
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.AllArguments Object[] arguments,
      @Advice.Thrown Throwable throwable) {
    if (throwable == null) {
      return;
    }

    ExceptionUtil.onException(typeName, methodName, arguments, throwable);
  }
}
