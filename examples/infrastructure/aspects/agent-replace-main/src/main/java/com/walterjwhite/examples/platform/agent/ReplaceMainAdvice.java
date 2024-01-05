package com.walterjwhite.examples.platform.agent;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.annotation.Transformer;
import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Transformer(
    configurationClass = TransformInstanceConfiguration.class,
    pattern = "transform/replace_main")
public class ReplaceMainAdvice {
  @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
  public static Object onMethodEnter(
      final @Advice.Origin Method method,
      @Advice.AllArguments(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object[] arguments) {
    System.exit(0);

    return null;
  }

  public static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final TransformInstanceConfiguration transformInstanceConfiguration,
      final Method method) {}
}
