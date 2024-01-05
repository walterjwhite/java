package com.walterjwhite.logging.plugin.advice;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.ContextualDataUtil;
import com.walterjwhite.logging.util.ExceptionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldContextualExceptionAdvice {
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

    ExceptionUtil.onException(typeName, methodName, arguments, throwable);
    LogLevel.ERROR.log(
        LoggerFactory.getLogger(typeName),
        "Contextual Details:\n{}",
        ContextualDataUtil.getContextualData(intercepted));
  }
}
