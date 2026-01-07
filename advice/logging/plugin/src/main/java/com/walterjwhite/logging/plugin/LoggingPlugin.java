package com.walterjwhite.logging.plugin;

import com.walterjwhite.logging.ContextualLoggable;
import com.walterjwhite.logging.FieldContextualLoggable;
import com.walterjwhite.logging.advice.exception.ContextualExceptionAdvice;
import com.walterjwhite.logging.advice.exception.ExceptionAdvice;
import com.walterjwhite.logging.advice.exception.FieldContextualExceptionAdvice;
import com.walterjwhite.logging.annotation.NonLoggable;
import com.walterjwhite.logging.enumeration.LogAdviceType;
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
    super(ElementMatchers.not(ElementMatchers.isAnnotatedWith(NonLoggable.class)));
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
        typeDescription.getDeclaredMethods().filter(includeLoggingEligibleMethods())) {
      if (isInfrastructure(typeDescription, methodDescription)) {
        LOGGER.info(
            "skipping additional logging on infrastructure method:"
                + typeDescription.getName()
                + "."
                + methodDescription.getName());
        continue;
      }

      LOGGER.debug("inspecting: {}", methodDescription);

      final Class adviceClass = LogAdviceType.getAdvice(methodDescription);
      if (adviceClass == null) {
        LOGGER.info("no advice for: {}", methodDescription);
        continue;
      }

      try {
        builder = builder.visit(Advice.to(adviceClass).on(ElementMatchers.is(methodDescription)));

        builder = handleExceptions(builder, typeDescription, methodDescription);
      } catch (IllegalStateException e) {
        LOGGER.error("Unable to apply advice: {} {}", adviceClass, methodDescription);
        LOGGER.error("exception:", e);
      } catch (Exception e) {
        LOGGER.error("exception:", e);
      }
    }

    LOGGER.info("completed");
    return builder;
  }

  private static Junction<MethodDescription> includeLoggingEligibleMethods() {
    return ElementMatchers.not(ElementMatchers.isBridge())
        .and(ElementMatchers.not(ElementMatchers.isAnnotatedWith(NonLoggable.class)));
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
    if (methodDescription.isConstructor()) {
      LOGGER.info("no exception advice on constructor");
      return builder;
    }

    if (methodDescription.isStatic()) {
      LOGGER.info(
          "Applying ExceptionAdvice:"
              + typeDescription.getName()
              + ", "
              + methodDescription.getName());
      return builder.visit(
          Advice.to(ExceptionAdvice.class).on(ElementMatchers.is(methodDescription)));
    }

    if (typeDescription.isAssignableTo(ContextualLoggable.class)) {
      LOGGER.info(
          "Applying ContextualExceptionAdvice:"
              + typeDescription.getName()
              + ", "
              + methodDescription.getName());
      return builder.visit(
          Advice.to(ContextualExceptionAdvice.class).on(ElementMatchers.is(methodDescription)));
    }
    if (typeDescription.isAssignableTo(FieldContextualLoggable.class)) {
      LOGGER.info(
          "Applying FieldContextualExceptionAdvice:"
              + typeDescription.getName()
              + ", "
              + methodDescription.getName());
      return builder.visit(
          Advice.to(FieldContextualExceptionAdvice.class)
              .on(ElementMatchers.is(methodDescription)));
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
