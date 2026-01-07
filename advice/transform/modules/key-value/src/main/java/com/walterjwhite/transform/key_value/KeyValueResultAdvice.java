package com.walterjwhite.transform.key_value;

import com.walterjwhite.file.modules.resources.JarReadUtils;
import com.walterjwhite.serialization.api.service.SerializationService;
import com.walterjwhite.transform.ArgumentTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

@Transformer(
    configurationClass = KeyValueResultConfiguration.class,
    pattern = "transform/key_value/result")
public class KeyValueResultAdvice {
  public static final transient Map<String, Object> RESULT_MAP = new HashMap<>();

  @Advice.OnMethodEnter(skipOn = Advice.OnNonDefaultValue.class)
  public static Object onMethodEnter(final @Advice.Origin Method method) {
    final Object transformed = getResult(method);
    return transformed;
  }

  @Advice.OnMethodExit
  public static void onMethodExit(
      final @Advice.Origin Method method,
      @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object returned,
      @Advice.Enter Object enter) {
    if (enter != null) {
      returned = enter;
    }
  }

  public static void init(
      final AgentBuilder agentBuilder,
      final Instrumentation instrumentation,
      final SerializationService serializationService,
      final KeyValueResultConfiguration keyValueResultConfiguration,
      final Method method) {
    putResult(method, getResult(serializationService, keyValueResultConfiguration));
  }

  public static Object getResult(
      final SerializationService serializationService,
      final KeyValueResultConfiguration keyValueResultConfiguration) {
    if (keyValueResultConfiguration.getResultClass() != null) {
      try {
        final Optional<ValueType> valueTypeOptional =
            ValueType.get(keyValueResultConfiguration.getResultClass());
        if (valueTypeOptional.isPresent()) {
          return valueTypeOptional
              .get()
              .getConversionFunction()
              .apply(keyValueResultConfiguration.getResult());
        }

        return serializationService.deserialize(
            JarReadUtils.getFileFromResourceAsStream(keyValueResultConfiguration.getResult()),
            getResultClass(keyValueResultConfiguration.getResultClass()));
      } catch (IOException | ClassNotFoundException e) {
        throw new Error("Unable to deserialize", e);
      }
    }

    return keyValueResultConfiguration.getResult();
  }

  public static Class getResultClass(final String resultClassName) throws ClassNotFoundException {
    return Class.forName(resultClassName);
  }

  public static void putResult(final Method method, final Object value) {
    RESULT_MAP.put(getResultKey(method), value);
  }

  public static Object getResult(final Method method) {
    return RESULT_MAP.get(getResultKey(method));
  }

  public static String getResultKey(final Method method) {
    return ArgumentTransformer.getMethodKey(method);
  }
}
