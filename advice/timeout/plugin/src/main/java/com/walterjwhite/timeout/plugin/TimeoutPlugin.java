package com.walterjwhite.timeout.plugin;

import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import com.walterjwhite.timeout.runtime.TimeoutAdvice;
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
                    .and(ElementMatchers.not(ElementMatchers.isConstructor())))
            .and(ElementMatchers.isSubTypeOf(TimeConstrainedMethodInvocation.class)));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription.getDeclaredMethods().filter(includeTimeoutEligibleMethods())) {

      builder =
          builder.visit(Advice.to(TimeoutAdvice.class).on(ElementMatchers.is(methodDescription)));
    }

    return builder;
  }

  private static Junction<MethodDescription> includeTimeoutEligibleMethods() {
    return ElementMatchers.not(
            ElementMatchers.isBridge()
                .and(ElementMatchers.isAbstract())
                .and(ElementMatchers.isConstructor()))
        .and(ElementMatchers.isAnnotatedWith(TimeConstrained.class));
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
