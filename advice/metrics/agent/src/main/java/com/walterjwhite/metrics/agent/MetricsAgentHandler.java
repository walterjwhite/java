package com.walterjwhite.metrics.agent;

import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import com.walterjwhite.metrics.annotation.Metrics;
import com.walterjwhite.metrics.enumeration.MetricsType;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
public class MetricsAgentHandler implements AgentHandler {

  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    final AgentBuilder agentBuilder =
        new AgentBuilder.Default().with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

    agentBuilder.installOn(agentApplicationInstance.getInstrumentation());
    agentApplicationInstance
        .getReflections()
        .getMethodsAnnotatedWith(Metrics.class)
        .forEach(metricsMethod -> setupResilience4j(agentBuilder, metricsMethod));
  }

  public static void setupResilience4j(
      final AgentBuilder agentBuilder, final Method metricsMethod) {
    Arrays.stream(metricsMethod.getAnnotation(Metrics.class).value())
        .forEach(metricsType -> setupMetrics(agentBuilder, metricsMethod, metricsType));
  }

  public static void setupMetrics(
      final AgentBuilder agentBuilder, final Method metricsMethod, final MetricsType metricsType) {
    final Class interceptorClass = null;
    LOGGER.info(
        "Installing {} on {}.{}",
        interceptorClass,
        metricsMethod.getDeclaringClass(),
        metricsMethod.getName());

    agentBuilder
        .type(ElementMatchers.is(metricsMethod.getDeclaringClass()))
        .transform(
            (builder, type, classLoader, module, other) ->
                builder
                    .method(ElementMatchers.named(metricsMethod.getName()))
                    .intercept(MethodDelegation.to(interceptorClass)));
  }
}
