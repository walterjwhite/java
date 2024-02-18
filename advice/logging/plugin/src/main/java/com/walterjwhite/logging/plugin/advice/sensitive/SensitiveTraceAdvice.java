package com.walterjwhite.logging.plugin.advice.sensitive;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.util.MethodLogUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.lang.reflect.Executable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SensitiveTraceAdvice {
  @Advice.OnMethodEnter
  public static long onEnter(
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.Origin final Executable executable,
      @Advice.AllArguments Object[] arguments) {
    return MethodLogUtil.onEnter(LogLevel.TRACE, typeName, methodName, MethodLogUtil.getSensitives(executable), arguments);
  }

  @Advice.OnMethodExit
  public static void onExit(
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.Origin final Executable executable,
      @Advice.AllArguments Object[] arguments,
      @Advice.Return(typing = Assigner.Typing.DYNAMIC) Object result,
      @Advice.Enter final long startTime) {
    final Sensitive sensitive = executable.getDeclaredAnnotation(Sensitive.class);
    MethodLogUtil.onExit(LogLevel.TRACE, typeName, methodName, MethodLogUtil.getSensitives(executable), arguments, result, startTime, sensitive.value());
  }
}
