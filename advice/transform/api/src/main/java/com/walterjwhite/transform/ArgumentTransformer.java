package com.walterjwhite.transform;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentTransformer {
  public static final String DELIMETER = ".";

  public static final ThreadLocal<Method> INVOKED_METHOD = new ThreadLocal<>();
  public static final ThreadLocal<Integer> INVOKED_METHOD_PARAMETER_INDEX = new ThreadLocal<>();

  public static Object[] transform(
      final Method method, Object[] arguments, final Function<Object, Object> function) {
    try {
      INVOKED_METHOD.set(method);
      INVOKED_METHOD_PARAMETER_INDEX.set(0);
      return (Object[]) TransformedType.get(arguments).transform(arguments, function);
    } catch (IllegalArgumentException e) {
      return arguments;
    } finally {
      INVOKED_METHOD_PARAMETER_INDEX.remove();
      INVOKED_METHOD.remove();
    }
  }

  public static String getCurrentMethodParameterName() {
    final int index = getCurrentMethodParameterIndex();

    return INVOKED_METHOD.get().getParameters()[index].getName();
  }

  public static Integer getCurrentMethodParameterIndex() {
    final Integer currentMethodIndex = INVOKED_METHOD_PARAMETER_INDEX.get();

    INVOKED_METHOD_PARAMETER_INDEX.set(currentMethodIndex.intValue() + 1);
    return currentMethodIndex;
  }

  public static Method getCurrentMethod() {
    return INVOKED_METHOD.get();
  }

  public static String getMethodKey(final Method method) {
    return String.join(DELIMETER, method.getDeclaringClass().getName(), method.getName());
  }

  public static String getArgumentKey(final Method method, final int argumentIndex) {
    return String.join(DELIMETER, getMethodKey(method), Integer.toString(argumentIndex));
  }

  public static String getArgumentKey(
      final String className, final String methodName, final String argumentName) {
    return String.join(DELIMETER, getMethodKey(getMethod(className, methodName)), argumentName);
  }

  public static Method getMethod(final String className, final String methodName) {
    final Optional<Method> method =
        Arrays.stream(TransformUtil.getClass(className).getDeclaredMethods())
            .filter(m -> m.getName().equals(methodName))
            .findFirst();
    if (method.isEmpty()) {
      throw new Error("Unable to intercept method", new NoSuchMethodException(methodName));
    }

    return method.get();
  }
}
