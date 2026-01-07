package com.walterjwhite.closeable.agent;

import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import java.lang.reflect.Constructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
public class CloseableAgentHandler implements AgentHandler {

  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    final AgentBuilder agentBuilder =
        new AgentBuilder.Default().with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

    agentBuilder.installOn(agentApplicationInstance.getInstrumentation());
    agentApplicationInstance
        .getReflections()
        .getSubTypesOf(AutoCloseable.class)
        .forEach(closeable -> setupCloseable(agentBuilder, closeable));
  }

  public static void setupCloseable(
      final AgentBuilder agentBuilder, final Class<? extends AutoCloseable> closeableClass) {
    LOGGER.info("Installing CloseableAdvice on {}", closeableClass);

    for (final Constructor constructor : closeableClass.getDeclaredConstructors()) {
      agentBuilder
          .type(ElementMatchers.is(closeableClass))
          .transform(
              (builder, type, classLoader, module, other) ->
                  builder.visit(Advice.to(closeableClass).on(ElementMatchers.is(constructor))));
    }
  }
}
