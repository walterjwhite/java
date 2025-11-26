package com.walterjwhite.resilience4j.interceptor;

import com.walterjwhite.resilience4j.InterceptorType;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

@Slf4j
public class BulkheadInterceptor {
  @RuntimeType
  public static Object intercept(
      @Origin Method method,
      @SuperCall Callable<?> callable) throws Exception {
    LOGGER.info("intercepting: {}", method);
    System.out.println("intercepting: " + method);


    final BulkheadRegistry bulkheadRegistry =
        (BulkheadRegistry) InterceptorType.BulkHeadType.getRegistry();

    return callable.call();
  }
}
