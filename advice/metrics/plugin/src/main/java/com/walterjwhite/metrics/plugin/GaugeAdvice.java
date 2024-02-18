package com.walterjwhite.metrics.plugin;

import com.walterjwhite.metrics.plugin.enumeration.MetricType;
import io.micrometer.core.instrument.Gauge;
import net.bytebuddy.asm.Advice;

public class GaugeAdvice {
  @Advice.OnMethodEnter
  public static Gauge onEnter(@Advice.This Object intercepted) {
    if (MetricsRegistryHelper.isNoOperation()) {
      return null;
    }


    final Gauge guage = MetricsRegistryHelper.get(intercepted, MetricType.Gauge);
    

    return guage;
  }

  @Advice.OnMethodExit
  public static void onExit(@Advice.Enter Gauge gauge) {
    if (gauge == null) {
      return;
    }

    

  }
}
