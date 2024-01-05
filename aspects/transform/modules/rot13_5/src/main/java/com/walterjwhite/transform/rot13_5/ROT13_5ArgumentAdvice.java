package com.walterjwhite.transform.rot13_5;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.*;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Transformer(
    configurationClass = TransformInstanceConfiguration.class,
    pattern = "transform/rot13_5/argument")
public class ROT13_5ArgumentAdvice {
  public static final transient Set<String> ARGUMENT_MAP = new HashSet<>();

  @Advice.OnMethodEnter
  public static void onEnter(
      final @Advice.Origin Method method,
      @Advice.AllArguments(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object[] arguments) {
    arguments = ArgumentTransformer.transform(method, arguments, ROT13_5ArgumentAdvice::transform);
  }

  private static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final Method method,
      final ROT13_5ArgumentConfiguration rot13_5ArgumentConfiguration) {
    for (final int argumentIndex : rot13_5ArgumentConfiguration.getArguments()) {
      ARGUMENT_MAP.add(ArgumentTransformer.getArgumentKey(method, argumentIndex));
    }
  }

  // within a map or POJO
  public static Object transform(final Object input) {
    final Optional<String> matching =
        ARGUMENT_MAP.stream()
            .filter(
                s ->
                    s.equals(
                        ArgumentTransformer.getArgumentKey(
                            ArgumentTransformer.getCurrentMethod(),
                            ArgumentTransformer.getCurrentMethodParameterIndex())))
            .findFirst();
    if (matching.isEmpty()) {
      return input;
    }

    return ROT13_5ResultAdvice.transform(input);
  }
}
