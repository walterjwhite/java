package com.walterjwhite.resilience4j.interceptor;

import com.walterjwhite.resilience4j.InterceptorType;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

@Slf4j
public class RateLimitedInterceptor {
  @RuntimeType
  public static Object intercept(@SuperCall Callable<Object> originalCall, @Origin Method method)
      throws Throwable {
    final RateLimiterRegistry rateLimiterRegistry =
        (RateLimiterRegistry) InterceptorType.RatedLimitedType.getRegistry();

    final String rateLimiterConfigKey = InterceptorType.getName(method);
    final RateLimiterConfig rateLimiterConfig =
        rateLimiterRegistry.getConfiguration(rateLimiterConfigKey).orElseThrow();

    final RateLimiter rateLimiter =
        rateLimiterRegistry.rateLimiter(rateLimiterConfigKey, rateLimiterConfig);

    final Callable<Object> decorated = RateLimiter.decorateCallable(rateLimiter, originalCall);
    return decorated.call();
  }
}
