package com.walterjwhite.timeout.plugin;

import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;

@HashCodeAndEqualsPlugin.Enhance
public class TimeoutPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public TimeoutPlugin() {
    super(
        ElementMatchers.declaresMethod(
            ElementMatchers.isAnnotatedWith(TimeConstrained.class)
                .and(ElementMatchers.not(ElementMatchers.isConstructor()))));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    if (!typeDescription.isAssignableTo(TimeConstrainedMethodInvocation.class)) {
      throw new IllegalArgumentException(
          "@TimeConstrained cannot be on a method in a class that does not implement"
              + " TimeConstrainedMethodInvocation - please check your configuration");
    }

    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(
                ElementMatchers.not(
                        ElementMatchers.isBridge()
                            .and(ElementMatchers.isAbstract().and(ElementMatchers.isConstructor())))
                    .and(ElementMatchers.isAnnotatedWith(TimeConstrained.class)))) {

      // TimeConstrainedMethodInvocation; however, we can only have 1 duration for all of them
      // currently, we only use it on a single method in a class
      builder =
          builder.visit(Advice.to(TimeoutAdvice.class).on(ElementMatchers.is(methodDescription)));
    }

    return builder;
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
