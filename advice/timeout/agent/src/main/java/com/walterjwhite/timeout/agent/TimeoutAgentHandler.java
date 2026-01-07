package com.walterjwhite.timeout.agent;

import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import com.walterjwhite.timeout.runtime.TimeoutAdvice;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
public class TimeoutAgentHandler implements AgentHandler {

  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    final AgentBuilder agentBuilder =
        new AgentBuilder.Default().with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

    agentBuilder.installOn(agentApplicationInstance.getInstrumentation());

    agentApplicationInstance
        .getReflections()
        .getMethodsAnnotatedWith(TimeConstrained.class)
        .forEach(
            timeConstrainedMethod -> setupTimeConstrained(agentBuilder, timeConstrainedMethod));
  }

  public static void setupTimeConstrained(
      final AgentBuilder agentBuilder, final Method timeConstrainedMethod) {
    LOGGER.info(
        "Installing TimeConstrained on {}.{}",
        timeConstrainedMethod.getDeclaringClass(),
        timeConstrainedMethod.getName());

    agentBuilder
        .type(ElementMatchers.is(timeConstrainedMethod.getDeclaringClass()))
        .transform(
            (builder, type, classLoader, module, other) ->
                builder.visit(
                    Advice.to(TimeoutAdvice.class).on(ElementMatchers.is(timeConstrainedMethod))));
  }
}
