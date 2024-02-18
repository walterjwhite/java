package com.walterjwhite.transform.hash;

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
    configurationClass = HashArgumentConfiguration.class,
    pattern = "transform/hash/argument")
public class HashArgumentAdvice {
  public static final transient Map<String, String> ARGUMENT_HASH_MAP = new HashMap<>();

  @Advice.OnMethodEnter
  public static void onEnter(
      final @Advice.Origin Method method,
      @Advice.AllArguments(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object[] arguments) {
    arguments = ArgumentTransformer.transform(method, arguments, HashArgumentAdvice::hash);
  }

  public static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final HashArgumentConfiguration hashArgumentConfiguration,
      final Method method) {
    for (final int argumentIndex : hashArgumentConfiguration.getArguments()) {
      ARGUMENT_HASH_MAP.put(
          ArgumentTransformer.getArgumentKey(method, argumentIndex),
          hashArgumentConfiguration.getHashAlgorithm());
    }
  }

  public static Object hash(final Object input) {
    return hash(
        input,
        ARGUMENT_HASH_MAP.get(
            ArgumentTransformer.getArgumentKey(
                ArgumentTransformer.getCurrentMethod(),
                ArgumentTransformer.getCurrentMethodParameterIndex())));
  }

  public static Object hash(final Object input, final String hashAlgorithm) {
    if (hashAlgorithm == null) {
      return input;
    }

    return HashType.valueOf(hashAlgorithm).hash((String) input);
  }
}
