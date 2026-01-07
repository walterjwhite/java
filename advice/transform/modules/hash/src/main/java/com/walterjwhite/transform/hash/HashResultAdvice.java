package com.walterjwhite.transform.hash;

import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.ResultTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Transformer(configurationClass = HashResultConfiguration.class, pattern = "transform/hash/result")
public class HashResultAdvice {
  public static final transient Map<String, String> RESULT_HASH_MAP = new HashMap<>();

  @Advice.OnMethodExit
  public static void onExit(
      final @Advice.Origin Method method,
      @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object value) {
    final String messageDigestAlgorithm =
        RESULT_HASH_MAP.get(ArgumentTransformer.getMethodKey(method));
    if (messageDigestAlgorithm == null) {
      return;
    }

    value = ResultTransformer.transform(value, HashResultAdvice::hash);
  }

  public static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final HashResultConfiguration hashInstanceConfiguration,
      final Method method) {
    RESULT_HASH_MAP.put(
        ArgumentTransformer.getMethodKey(method), hashInstanceConfiguration.getHashAlgorithm());
  }

  public static Object hash(final Object input) {
    return HashArgumentAdvice.hash(
        input,
        RESULT_HASH_MAP.get(
            ArgumentTransformer.getMethodKey(ArgumentTransformer.getCurrentMethod())));
  }
}
