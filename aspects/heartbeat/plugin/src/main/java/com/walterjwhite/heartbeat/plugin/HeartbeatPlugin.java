package com.walterjwhite.heartbeat.plugin;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
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
public class HeartbeatPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public HeartbeatPlugin() {
    super(ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith(Heartbeat.class)));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    if (!typeDescription.isAssignableTo(Heartbeatable.class)) {
      throw new IllegalArgumentException(
          "@Heartbeat cannot be on a method in a class that does not implement Heartbeatable -"
              + " please check your configuration");
    }

    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(
                ElementMatchers.not(ElementMatchers.isBridge())
                    .and(ElementMatchers.isAnnotatedWith(Heartbeat.class)))) {
      if (methodDescription.isAbstract()) {
        continue;
      }
      if (methodDescription.isConstructor()) {
        throw new IllegalStateException(
            "Cannot implement heartbeat on a constructor: " + methodDescription);
      }

      LOGGER.info("implementing heartbeat on {}", methodDescription.getName());
      builder =
          builder.visit(Advice.to(HeartbeatAdvice.class).on(ElementMatchers.is(methodDescription)));
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
