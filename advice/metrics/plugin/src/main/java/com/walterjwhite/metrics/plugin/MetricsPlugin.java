package com.walterjwhite.metrics.plugin;

import com.walterjwhite.metrics.annotation.Metrics;
import com.walterjwhite.metrics.enumeration.MetricType;
import com.walterjwhite.metrics.enumeration.MetricsType;
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
public class MetricsPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public MetricsPlugin() {
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
                    .filter(includeMetricsEligibleMethods())) {
      LOGGER.info("capturing metrics on {}", methodDescription.getName());

      final Metrics metrics = methodDescription.getDeclaredAnnotations().ofType(Metrics.class).load();
      for (final MetricsType metricsType : metrics.value()) {
        final MetricType metricType = MetricType.get(metricsType);

        builder =
                builder.visit(Advice.to(metricType.getAdviceClass()).on(ElementMatchers.is(methodDescription)));
      }
    }

    return builder;
  }

  private static Junction<MethodDescription> includeMetricsEligibleMethods() {
    return ElementMatchers.not(ElementMatchers.isBridge())
            .and(ElementMatchers.not(ElementMatchers.isConstructor()))
            .and(ElementMatchers.not(ElementMatchers.isAbstract()).and(ElementMatchers.isAnnotatedWith(Metrics.class)));
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
