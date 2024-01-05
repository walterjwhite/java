package com.walterjwhite.logging.plugin;

import com.google.common.collect.MapMaker;
import com.google.common.util.concurrent.RateLimiter;
import com.walterjwhite.logging.enumeration.LogRate;
import java.util.Map;

public class RateLimitedLoggerRegistry {
  private static final Map<String, RateLimiter> EXECUTION_METHOD_MAP =
      new MapMaker().weakKeys().makeMap();

  public static RateLimiter get(final String methodKey) {
    if (!EXECUTION_METHOD_MAP.containsKey(methodKey)) {
      final RateLimiter rateLimiter = setup(methodKey);
      if (rateLimiter != null) EXECUTION_METHOD_MAP.put(methodKey, rateLimiter);
    }

    return EXECUTION_METHOD_MAP.get(methodKey);
  }

  private static RateLimiter setup(final String methodKey) {
    final double logRate = getLogRate(methodKey);
    if (logRate > 0) return RateLimiter.create(logRate);

    return null;
  }

  private static double getLogRate(final String methodKey) {
    //    if (methodKey.isAnnotationPresent(com.walterjwhite.logging.annotation.LogRate.class)) {
    //      return methodKey.getAnnotation(com.walterjwhite.logging.annotation.LogRate.class).value();
    //    }

    return LogRate.Default;
  }
}
