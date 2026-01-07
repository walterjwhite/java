package com.walterjwhite.resilience4j;

import com.walterjwhite.google.resilience4j.annotation.Bulkhead;
import com.walterjwhite.google.resilience4j.annotation.CircuitBreaker;
import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import com.walterjwhite.google.resilience4j.annotation.TimeLimited;
import com.walterjwhite.resilience4j.interceptor.BulkheadInterceptor;
import com.walterjwhite.resilience4j.interceptor.CircuitBreakerInterceptor;
import com.walterjwhite.resilience4j.interceptor.RateLimitedInterceptor;
import com.walterjwhite.resilience4j.interceptor.TimeLimitedInterceptor;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.internal.SchedulerFactory;
import io.github.resilience4j.core.Registry;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InterceptorType {
  BulkHeadType(Bulkhead.class, BulkheadInterceptor.class, BulkheadRegistry.ofDefaults()) {
    @Override
    public Function decorate(Object object, Function function) {
      return io.github.resilience4j.bulkhead.Bulkhead.decorateFunction(
          (io.github.resilience4j.bulkhead.Bulkhead) object, function);
    }
  },
  CircuitBreakerType(
      CircuitBreaker.class, CircuitBreakerInterceptor.class, CircuitBreakerRegistry.ofDefaults()) {
    @Override
    public Function decorate(Object object, Function function) {
      return io.github.resilience4j.circuitbreaker.CircuitBreaker.decorateFunction(
          (io.github.resilience4j.circuitbreaker.CircuitBreaker) object, function);
    }
  },
  RatedLimitedType(
      RateLimited.class, RateLimitedInterceptor.class, RateLimiterRegistry.ofDefaults()) {
    @Override
    public Function decorate(Object object, Function function) {
      return io.github.resilience4j.ratelimiter.RateLimiter.decorateFunction(
          (io.github.resilience4j.ratelimiter.RateLimiter) object, function);
    }
  },
  TimeLimitedType(
      TimeLimited.class, TimeLimitedInterceptor.class, TimeLimiterRegistry.ofDefaults()) {
    private static final ScheduledExecutorService sceduler =
        SchedulerFactory.getInstance().getScheduler();

    @Override
    public Function decorate(Object object, Function function) {
      final io.github.resilience4j.timelimiter.TimeLimiter timeLimiter =
          (io.github.resilience4j.timelimiter.TimeLimiter) object;
      return null;
    }
  };

  private final Class<? extends Annotation> annotationClass;
  private final Class interceptorClass;
  private final Registry registry;

  public abstract Function decorate(final Object object, final Function function);

  public Object decorate(
      final Object object,
      final Method method,
      final Object self,
      final Object[] arguments,
      final Object defaultValue) {
    if (method == null) {
      return defaultValue;
    }

    final Function f =
        decorate(
            object,
            o -> {
              try {
                return method.invoke(self, arguments);
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
              }
            });

    return f.apply(self);
  }

  public static String getName(final Method method) {
    return method.getDeclaringClass().getName() + "." + method.getName();
  }

  static {
    RateLimitedConfigUtil.init();
  }
}
