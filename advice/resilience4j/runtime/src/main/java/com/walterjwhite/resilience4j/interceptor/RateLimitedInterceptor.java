package com.walterjwhite.resilience4j.interceptor;

import com.walterjwhite.resilience4j.InterceptorType;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.bind.annotation.*;

public class RateLimitedInterceptor {
  @RuntimeType
  public static Object intercept(
      @This Object self,
      @Origin Method method,
      @AllArguments Object[] arguments,
      @Empty Object defaultValue) {

    final RateLimiterRegistry rateLimiterRegistry =
        (RateLimiterRegistry) InterceptorType.BulkHeadType.getRegistry();
    final RateLimiter rateLimiter =
        rateLimiterRegistry.rateLimiter(InterceptorType.getName(method));

    return InterceptorType.RatedLimitedType.decorate(
        rateLimiter, method, self, arguments, defaultValue);
  }
}
