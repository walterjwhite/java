package com.walterjwhite.closeable.plugin;

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
public class CloseablePlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public CloseablePlugin() {
    super(ElementMatchers.not(ElementMatchers.isAbstract()));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    if (!typeDescription.isAssignableTo(AutoCloseable.class)) {
      return builder;
    }

    // only modify the constructor ...
    for (final MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(
                ElementMatchers.not(ElementMatchers.isBridge())
                    .and(ElementMatchers.isConstructor())
                    .and(ElementMatchers.not(ElementMatchers.isAbstract())))) {
      if (methodDescription.isAbstract()) {
        throw new IllegalStateException(
            "This should not happen, we are excluding abstract methods");
      }

      LOGGER.info("automatically closing: " + typeDescription.getName());
      builder =
          builder.visit(Advice.to(CloseableAdvice.class).on(ElementMatchers.is(methodDescription)));
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
