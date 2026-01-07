package com.walterjwhite.heartbeat.plugin;

import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import com.walterjwhite.heartbeat.runtime.HeartbeatAdvice;
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
    super(
        ElementMatchers.declaresMethod(ElementMatchers.isAnnotatedWith(Heartbeat.class))
            .and(ElementMatchers.isSubTypeOf(Heartbeatable.class)));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription.getDeclaredMethods().filter(includeHeartbeatEligibleMethods())) {
      LOGGER.info("implementing heartbeat on {}", methodDescription.getName());
      builder =
          builder.visit(Advice.to(HeartbeatAdvice.class).on(ElementMatchers.is(methodDescription)));
    }

    return builder;
  }

  private static Junction<MethodDescription> includeHeartbeatEligibleMethods() {
    return ElementMatchers.not(ElementMatchers.isBridge())
        .and(ElementMatchers.not(ElementMatchers.isAbstract()))
        .and(ElementMatchers.not(ElementMatchers.isConstructor()))
        .and(ElementMatchers.isAnnotatedWith(Heartbeat.class));
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
