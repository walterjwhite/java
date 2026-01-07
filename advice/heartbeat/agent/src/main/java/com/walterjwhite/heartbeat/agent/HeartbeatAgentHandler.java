package com.walterjwhite.heartbeat.agent;

import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.heartbeat.runtime.HeartbeatAdvice;
import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
public class HeartbeatAgentHandler implements AgentHandler {
  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    LOGGER.info("initializing heartbeat agent");
    final AgentBuilder agentBuilder =
        new AgentBuilder.Default().with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

    agentBuilder.installOn(agentApplicationInstance.getInstrumentation());
    agentApplicationInstance
        .getReflections()
        .getMethodsAnnotatedWith(Heartbeat.class)
        .forEach(heartbeatMethod -> setupHeartbeat(agentBuilder, heartbeatMethod));
  }

  public static void setupHeartbeat(final AgentBuilder agentBuilder, final Method heartbeatMethod) {
    LOGGER.info(
        "Installing HeartbeatAdvice on {}.{}",
        heartbeatMethod.getDeclaringClass(),
        heartbeatMethod.getName());

    agentBuilder
        .type(ElementMatchers.is(heartbeatMethod.getDeclaringClass()))
        .transform(
            (builder, type, classLoader, module, other) ->
                builder.visit(
                    Advice.to(HeartbeatAdvice.class).on(ElementMatchers.is(heartbeatMethod))));
  }
}
