package com.walterjwhite.resilience4j.plugin;

import com.walterjwhite.google.guice.resilience4j.annotation.*;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import com.walterjwhite.resilience4j.interceptor.BulkheadInterceptor;

@Slf4j
@HashCodeAndEqualsPlugin.Enhance
public class Resilience4jPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public Resilience4jPlugin() {
    super(ElementMatchers.any());
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(isResilience4jEligible())) {
      LOGGER.warn("implementing resilience4j on {}", methodDescription.getName());

      final Class interceptorClass = BulkheadInterceptor.class;
      builder = builder.method(ElementMatchers.is(methodDescription)).intercept(MethodDelegation.to(interceptorClass));
    }
    return builder;
  }

  private static ElementMatcher<MethodDescription> isResilience4jEligible() {
    return ElementMatchers.not(ElementMatchers.isBridge())
            .and(ElementMatchers.not(ElementMatchers.isAbstract()))
            .and(ElementMatchers.not(ElementMatchers.isConstructor()))
            .and(ElementMatchers.isAnnotatedWith(Bulkhead.class)
                  .or(ElementMatchers.isAnnotatedWith(Cache.class))
                  .or(ElementMatchers.isAnnotatedWith(CircuitBreaker.class))
                  .or(ElementMatchers.isAnnotatedWith(RateLimited.class))
                  .or(ElementMatchers.isAnnotatedWith(Retry.class))
                  .or(ElementMatchers.isAnnotatedWith(TimeLimited.class)));
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
