package com.walterjwhite.metrics.plugin;

import com.walterjwhite.metrics.plugin.enumeration.CounterTag;
import com.walterjwhite.metrics.plugin.enumeration.MetricType;
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
            MetricType.Counter );
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

    final CounterTag counterTag;
    if (throwable == null) {
      counterTag = CounterTag.Exit;
    } else {
      counterTag = CounterTag.Catch;
    }

    final Counter counter =
        MetricsRegistryHelper.get(
            intercepted,
            MetricType.Counter );
    counter.increment();
  }
}
