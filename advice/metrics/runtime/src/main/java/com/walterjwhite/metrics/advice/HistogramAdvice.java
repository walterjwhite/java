package com.walterjwhite.metrics.advice;

import com.walterjwhite.metrics.enumeration.MetricType;
import com.walterjwhite.metrics.util.MetricsRegistryHelper;
import io.micrometer.core.instrument.distribution.Histogram;
import net.bytebuddy.asm.Advice;

public class HistogramAdvice {
  @Advice.OnMethodEnter
  public static Histogram onEnter(@Advice.This Object intercepted) {
    if (MetricsRegistryHelper.isNoOperation()) {
      return null;
    }

    final Histogram histogram =
        MetricsRegistryHelper.get(
            intercepted,
            MetricType.Histogram /*, new TagParameter(MeterAttribute.State, CounterTag.Enter)*/);

    return histogram;
  }

  @Advice.OnMethodExit(onThrowable = Throwable.class)
  public static void onException(
      @Advice.This final Object intercepted,
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.AllArguments Object[] arguments,
      @Advice.Thrown Throwable throwable) {
    if (MetricsRegistryHelper.isNoOperation()) {
      return;
    }

    final Histogram histogram =
        MetricsRegistryHelper.get(
            intercepted,
            MetricType.Histogram /*, new TagParameter(MeterAttribute.State, counterTag)*/);
  }
}
