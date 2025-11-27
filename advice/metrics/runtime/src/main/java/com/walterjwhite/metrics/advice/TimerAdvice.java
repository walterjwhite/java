package com.walterjwhite.metrics.advice;

import com.walterjwhite.metrics.util.MetricsRegistryHelper;
import com.walterjwhite.metrics.enumeration.MetricType;
import io.micrometer.core.instrument.Timer;
import java.util.concurrent.TimeUnit;
import net.bytebuddy.asm.Advice;

public class TimerAdvice {
  @Advice.OnMethodEnter
  public static long onEnter(@Advice.This Object intercepted) {
    if (MetricsRegistryHelper.isNoOperation()) {
      return 0;
    }

    return System.nanoTime();
  }

  @Advice.OnMethodExit(onThrowable = Throwable.class)
  public static void onException(
      @Advice.This final Object intercepted,
      @Advice.Origin("#t") final String typeName,
      @Advice.Origin("#m") final String methodName,
      @Advice.AllArguments Object[] arguments,
      @Advice.Thrown Throwable throwable,
      final long startTimeInNanoseconds) {
    if (startTimeInNanoseconds == 0) {
      return;
    }

    final Timer timer = MetricsRegistryHelper.get(intercepted, MetricType.Timer);
    timer.record(System.nanoTime() - startTimeInNanoseconds, TimeUnit.NANOSECONDS);
  }
}
