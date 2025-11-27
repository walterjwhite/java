package com.walterjwhite.examples.proxy;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bind.annotation.*;

@Slf4j
public class MethodInterceptor {
  @RuntimeType
  public static void intercept(
      @Origin(cache = false) Method method, @RuntimeType @AllArguments Object[] allArguments) {
    LOGGER.info("intercept: {}({})", method, allArguments);
  }
}
