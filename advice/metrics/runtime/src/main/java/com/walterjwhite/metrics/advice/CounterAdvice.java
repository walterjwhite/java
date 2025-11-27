package com.walterjwhite.metrics.advice;

import com.walterjwhite.metrics.util.MetricsRegistryHelper;
import com.walterjwhite.metrics.enumeration.CounterTag;
import com.walterjwhite.metrics.enumeration.MetricType;
import io.micrometer.core.instrument.Counter;
import net.bytebuddy.asm.Advice;

public class CounterAdvice {
  @Advice.OnMethodEnter
  public static Counter onEnter(@Advice.This Object intercepted) {
    if (MetricsRegistryHelper.isNoOperation()) {
      return null;
    }

    final Counter counter =
        MetricsRegistryHelper.get(
            intercepted,
            MetricType.Counter/*, new TagParameter(MeterAttribute.State, CounterTag.Enter)*/);
    counter.increment();
    return counter;
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

    final CounterTag counterTag = getCounterTag(throwable);

    final Counter counter =
        MetricsRegistryHelper.get(
            intercepted,
            MetricType.Counter /*, new TagParameter(MeterAttribute.State, counterTag)*/);
    counter.increment();
  }

  public static CounterTag getCounterTag(final Throwable throwable) {
    if (throwable == null) {
      return CounterTag.Exit;
    }

    return CounterTag.Catch;
  }
}
