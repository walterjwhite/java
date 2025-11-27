package com.walterjwhite.delay.agent;

import com.walterjwhite.delay.DelayAdvice;
import com.walterjwhite.delay.annotation.Delayed;
import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;

@Slf4j
public class DelayAgentHandler implements AgentHandler {
    @Override
    public void initialize(final AgentApplicationInstance agentApplicationInstance) {
        final AgentBuilder agentBuilder = new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.REDEFINITION);

        agentBuilder.installOn(agentApplicationInstance.getInstrumentation());
        agentApplicationInstance.getReflections().getMethodsAnnotatedWith(Delayed.class).forEach(delayedMethod -> setupDelay(agentBuilder, delayedMethod));
    }

    public static void setupDelay(
            final AgentBuilder agentBuilder, final Method delayedMethod) {
        LOGGER.info("Installing DelayAdvice on {}.{}", delayedMethod.getDeclaringClass(), delayedMethod.getName());

        agentBuilder.type(ElementMatchers.is(delayedMethod.getDeclaringClass()))
                .transform((builder, type, classLoader, module, other) ->
                        builder.visit(Advice.to(DelayAdvice.class).on(ElementMatchers.is(delayedMethod))));
    }
}
