package com.walterjwhite.delay.plugin;

import com.walterjwhite.delay.annotation.Delayed;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
@HashCodeAndEqualsPlugin.Enhance
public class DelayPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public DelayPlugin() {
    super(ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith(Delayed.class)));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(
                ElementMatchers.not(ElementMatchers.isBridge())
                    .and(ElementMatchers.not(ElementMatchers.isConstructor()))
                    .and(ElementMatchers.not(ElementMatchers.isAbstract()))
                    .and(ElementMatchers.isAnnotatedWith(Delayed.class)))) {

      // determine which argument has the delayed data and configure the delay

      LOGGER.info("implementing delay on {}", methodDescription.getName());
      builder =
          builder.visit(Advice.to(DelayAdvice.class).on(ElementMatchers.is(methodDescription)));
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
