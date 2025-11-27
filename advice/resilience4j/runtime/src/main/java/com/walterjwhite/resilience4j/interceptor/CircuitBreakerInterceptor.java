package com.walterjwhite.resilience4j.interceptor;

import com.walterjwhite.resilience4j.InterceptorType;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.lang.reflect.Method;
import net.bytebuddy.implementation.bind.annotation.*;

public class CircuitBreakerInterceptor {
  @RuntimeType
  public static Object intercept(
      @This Object self,
      @Origin Method method,
      @AllArguments Object[] arguments,
      @Empty Object defaultValue) {

    final CircuitBreakerRegistry circuitBreakerRegistry =
        (CircuitBreakerRegistry) InterceptorType.CircuitBreakerType.getRegistry();
    final CircuitBreaker circuitBreaker =
        circuitBreakerRegistry.circuitBreaker(InterceptorType.getName(method));

    return InterceptorType.CircuitBreakerType.decorate(
        circuitBreaker, method, self, arguments, defaultValue);
  }
}
