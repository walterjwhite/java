package com.walterjwhite.logging.advice.std;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.MethodLogUtil;
import java.lang.reflect.Executable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InfoAdvice {
  @Advice.OnMethodEnter
  public static long onEnter(
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.Origin final Executable executable,
      @Advice.AllArguments Object[] arguments) {
    return MethodLogUtil.onEnter(
        LogLevel.INFO, typeName, methodName, MethodLogUtil.getSensitives(executable), arguments);
  }

  @Advice.OnMethodExit
  public static void onExit(
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.Origin final Executable executable,
      @Advice.AllArguments Object[] arguments,
      @Advice.Return(typing = Assigner.Typing.DYNAMIC) Object result,
      @Advice.Enter final long startTime) {
    MethodLogUtil.onExit(
        LogLevel.INFO,
        typeName,
        methodName,
        MethodLogUtil.getSensitives(executable),
        arguments,
        result,
        startTime,
        -1);
  }
}
