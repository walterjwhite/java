package com.walterjwhite.logging.plugin.enumeration;

import com.walterjwhite.logging.annotation.NonLoggable;
import com.walterjwhite.logging.annotation.Sensitive;
import java.util.Optional;
import net.bytebuddy.description.method.MethodDescription;

public enum LogAdviceType {
  General {
    @Override
    public Class doGetAdvice(final MethodDescription methodDescription) {
      final Optional<GeneralType> generalTypeOptional = GeneralType.getAdvice(methodDescription);
      if (generalTypeOptional.isPresent()) {
        return generalTypeOptional.get().getAdviceClass();
      }

      return null;
    }
  };

  public abstract Class doGetAdvice(final MethodDescription methodDescription);

  public static Class getAdvice(final MethodDescription methodDescription) {
    if (!isAdvisable(methodDescription)) {
      return null;
    }

    for (LogAdviceType logAdviceType : values()) {
      final Class adviceClass = logAdviceType.doGetAdvice(methodDescription);
      if (adviceClass != null) {
        return adviceClass;
      }
    }

    return null;
  }

  private static boolean isAdvisable(final MethodDescription methodDescription) {
    if (methodDescription.getDeclaredAnnotations().ofType(NonLoggable.class) != null) {
      return false;
    }

    if (methodDescription.getDeclaredAnnotations().ofType(Sensitive.class) != null) {
      return false;
    }

    if (methodDescription.isAbstract()) {
      return false;
    }

    return !methodDescription.getDeclaringType().isEnum()
        || !methodDescription.getActualName().equals("values");
  }
}
