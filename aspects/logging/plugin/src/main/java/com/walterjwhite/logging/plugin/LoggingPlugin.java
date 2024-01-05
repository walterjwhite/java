package com.walterjwhite.logging.plugin;

import static net.bytebuddy.matcher.ElementMatchers.is;

import com.walterjwhite.logging.ContextualLoggable;
import com.walterjwhite.logging.FieldContextualLoggable;
import com.walterjwhite.logging.annotation.NonLoggable;
import com.walterjwhite.logging.plugin.advice.ContextualExceptionAdvice;
import com.walterjwhite.logging.plugin.advice.ExceptionAdvice;
import com.walterjwhite.logging.plugin.advice.FieldContextualExceptionAdvice;
import com.walterjwhite.logging.plugin.enumeration.LogAdviceType;
import java.lang.reflect.Method;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;

@Slf4j
@HashCodeAndEqualsPlugin.Enhance
public class LoggingPlugin extends Plugin.ForElementMatcher implements Plugin.Factory {
  public LoggingPlugin() {
    super(
        ElementMatchers.declaresMethod(
            ElementMatchers.not(ElementMatchers.isAnnotatedWith(NonLoggable.class))));
  }

  @Override
  public DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassFileLocator classFileLocator) {
    return apply(builder, typeDescription);
  }

  public static DynamicType.Builder<?> apply(
      DynamicType.Builder<?> builder, TypeDescription typeDescription) {
    for (MethodDescription.InDefinedShape methodDescription :
        typeDescription
            .getDeclaredMethods()
            .filter(ElementMatchers.not(ElementMatchers.isBridge()))) {
      // wire in info and exception advice
      final Class adviceClass = LogAdviceType.getAdvice(methodDescription);
      if (adviceClass == null) {
        continue;
      }

      if (isInfrastructure(typeDescription, methodDescription)) {
        LOGGER.warn(
            "skipping additional logging on infrastructure method:"
                + typeDescription.getName()
                + "."
                + methodDescription.getName());
        continue;
      }

      try {
        builder = builder.visit(Advice.to(adviceClass).on(is(methodDescription)));

        // 2022/04/17 - previously disabled
        // exceptions may be caught more than once by this
        // previously used a ThreadLocal + WeakReference, but that also doesn't seem ideal
        builder = handleExceptions(builder, typeDescription, methodDescription);
      } catch (IllegalStateException e) {
        LOGGER.warn(
            String.format("Unable to apply advice: %s %s", adviceClass, methodDescription), e);
      }
    }

    return builder;
  }

  private static boolean isInfrastructure(
      final TypeDescription typeDescription, final MethodDescription methodDescription) {
    if (typeDescription.isAssignableTo(ContextualLoggable.class)) {
      return Arrays.stream(ContextualLoggable.class.getDeclaredMethods())
          .filter(
              method ->
                  method.getName().equals(methodDescription.getName())
                      && matchesParameters(method, methodDescription))
          .findFirst()
          .isPresent();
    }

    return false;
  }

  private static boolean matchesParameters(
      final Method method, final MethodDescription methodDescription) {
    if (method.getParameterCount() != methodDescription.getParameters().size()) {
      return false;
    }

    int i = 0;
    for (Class<?> parameterType : method.getParameterTypes()) {
      if (parameterType.equals(methodDescription.getParameters().get(i))) {
        return false;
      }

      i++;
    }

    return true;
  }

  private static DynamicType.Builder<?> handleExceptions(
      final DynamicType.Builder<?> builder,
      final TypeDescription typeDescription,
      final MethodDescription methodDescription) {
    if (!methodDescription.isConstructor()) {
      if (!methodDescription.isStatic()) {
        if (typeDescription.isAssignableTo(ContextualLoggable.class)) {
          LOGGER.info(
              "Applying ContextualExceptionAdvice:"
                  + typeDescription.getName()
                  + ", "
                  + methodDescription.getName());
          return builder.visit(
              Advice.to(ContextualExceptionAdvice.class).on(is(methodDescription)));
        }
        if (typeDescription.isAssignableTo(FieldContextualLoggable.class)) {
          LOGGER.info(
              "Applying FieldContextualExceptionAdvice:"
                  + typeDescription.getName()
                  + ", "
                  + methodDescription.getName());
          return builder.visit(
              Advice.to(FieldContextualExceptionAdvice.class).on(is(methodDescription)));
        }
      }

      LOGGER.info(
          "Applying ExceptionAdvice:"
              + typeDescription.getName()
              + ", "
              + methodDescription.getName());
      return builder.visit(Advice.to(ExceptionAdvice.class).on(is(methodDescription)));
    }

    return builder;
  }

  @Override
  public void close() {}

  @Override
  public Plugin make() {
    return this;
  }
}
