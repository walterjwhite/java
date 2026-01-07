package com.walterjwhite.resilience4j.plugin;

import com.walterjwhite.resilience4j.InterceptorType;
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
        typeDescription.getDeclaredMethods().filter(isResilience4jEligible())) {

      for (final InterceptorType interceptorType : InterceptorType.values()) {
        if (methodDescription.getDeclaredAnnotations().ofType(interceptorType.getAnnotationClass())
            != null) {
          LOGGER.info(
              "implementing resilience4j on {} with {}",
              methodDescription.getName(),
              interceptorType.name());
          builder =
              builder
                  .method(ElementMatchers.is(methodDescription))
                  .intercept(MethodDelegation.to(interceptorType.getInterceptorClass()));
        }
      }
    }
    return builder;
  }

  private static ElementMatcher<MethodDescription> isResilience4jEligible() {
    return ElementMatchers.not(ElementMatchers.isBridge())
        .and(ElementMatchers.not(ElementMatchers.isAbstract()))
        .and(ElementMatchers.not(ElementMatchers.isConstructor()));
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
