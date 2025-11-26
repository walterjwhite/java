package com.walterjwhite.resilience4j.agent;

import com.walterjwhite.resilience4j.InterceptorType;

import com.walterjwhite.inject.cli.AgentApplicationInstance;
import com.walterjwhite.inject.cli.AgentHandler;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.reflect.Method;

@Slf4j
public class Resilience4jAgentHandler implements AgentHandler {

  @Override
  public void initialize(final AgentApplicationInstance agentApplicationInstance) {
    final AgentBuilder agentBuilder = new AgentBuilder.Default()
            .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
            .disableClassFormatChanges()
        .with( new AgentBuilder.Listener() {

          @Override
          public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
            LOGGER.info("onDiscovery: {} {} {} {}", s, classLoader, javaModule, b);
          }

          @Override
          public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
            LOGGER.info("onTransformation: {} {} {} {} {}", typeDescription, classLoader, javaModule, b, dynamicType);
          }

          @Override
          public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
            LOGGER.info("onIgnored: {} {} {} {}", typeDescription, classLoader, javaModule, b);
          }

          @Override
          public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
            LOGGER.info("onError: {} {} {} {} {}", s, classLoader, javaModule, b, throwable);
          }

          @Override
          public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
            LOGGER.info("onComplete: {} {} {} {}", s, classLoader, javaModule, b);
          }
    });


    for (final InterceptorType interceptorType : InterceptorType.values()) {
      for (final Method method :
              agentApplicationInstance.getReflections().getMethodsAnnotatedWith(interceptorType.getAnnotationClass())) {
        setupResilience4j(agentApplicationInstance, agentBuilder, interceptorType, method);
      }
    }
  }

  public static void setupResilience4j(final AgentApplicationInstance agentApplicationInstance,
          final AgentBuilder agentBuilder, final InterceptorType interceptorType, final Method method) {
    LOGGER.info("Installing {} on {}.{}", interceptorType.getInterceptorClass(), method.getDeclaringClass(), method.getName());

    ElementMatcher.Junction<MethodDescription> executeMethodDecription = ElementMatchers.isMethod()
              .and(ElementMatchers.named(method.getName()))
              .and(ElementMatchers.not(ElementMatchers.isAbstract()))
              .and(ElementMatchers.is(method));

    agentBuilder.type(ElementMatchers.is(method.getDeclaringClass()))
    .transform((builder, type, classLoader, module, other) ->
      builder.method(executeMethodDecription).intercept(MethodDelegation.to(interceptorType.getInterceptorClass()))).installOn(agentApplicationInstance.getInstrumentation());
  }
}
