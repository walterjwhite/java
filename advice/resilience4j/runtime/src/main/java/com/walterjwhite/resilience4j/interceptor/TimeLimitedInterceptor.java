package com.walterjwhite.resilience4j.interceptor;

import com.walterjwhite.resilience4j.InterceptorType;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

public class TimeLimitedInterceptor {
  @RuntimeType
  public static Object intercept(
      @This Object self,
      @Origin Method method,
      @AllArguments Object[] arguments,
      @Empty Object defaultValue) {

    final TimeLimiterRegistry timeLimiterRegistry =
        (TimeLimiterRegistry) InterceptorType.TimeLimitedType.getRegistry();
    final TimeLimiter timeLimiter =
            timeLimiterRegistry.timeLimiter(InterceptorType.getName(method));

    return InterceptorType.TimeLimitedType.decorate(
        timeLimiter, method, self, arguments, defaultValue);
  }
}
