package com.walterjwhite.transform.key_value;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Transformer(
    configurationClass = KeyValueArgumentConfiguration.class,
    pattern = "transform/key_value/argument")
public class KeyValueArgumentAdvice {
  public static final transient Map<String, String> ARGUMENT_MAP = new HashMap<>();

  @Advice.OnMethodEnter
  public static void onEnter(
      final @Advice.Origin Method method,
      @Advice.AllArguments(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object[] arguments) {
    arguments =
        ArgumentTransformer.transform(method, arguments, KeyValueArgumentAdvice::transformArgument);
  }

  public static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final KeyValueArgumentConfiguration keyValueArgumentConfiguration,
      final Method method) {
    for (final Map.Entry<Integer, String> entry :
        keyValueArgumentConfiguration.getArgumentMap().entrySet()) {
      putArgument(method, entry.getKey(), entry.getValue());
    }
  }

  public static Object transformArgument(final Object input) {
    final String transformed =
        getArgument(
            ArgumentTransformer.getCurrentMethod(),
            ArgumentTransformer.getCurrentMethodParameterIndex());
    if (transformed != null) {
      return transformed;
    }

    return input;
  }

  public static void putArgument(final Method method, final int argumentIndex, final String value) {
    ARGUMENT_MAP.put(ArgumentTransformer.getArgumentKey(method, argumentIndex), value);
  }

  public static String getArgument(final Method method, final int argumentIndex) {
    return ARGUMENT_MAP.get(ArgumentTransformer.getArgumentKey(method, argumentIndex));
  }
}
