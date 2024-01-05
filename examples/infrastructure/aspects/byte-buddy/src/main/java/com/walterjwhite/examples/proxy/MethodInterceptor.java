package com.walterjwhite.examples.proxy;

import java.lang.reflect.Method;
import net.bytebuddy.implementation.bind.annotation.*;

public class MethodInterceptor {
  @RuntimeType
  public static void intercept(
      @Origin(cache = false) Method method, @RuntimeType @AllArguments Object[] allArguments) {}
}
