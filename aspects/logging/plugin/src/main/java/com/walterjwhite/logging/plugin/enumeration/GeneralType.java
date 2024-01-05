package com.walterjwhite.logging.plugin.enumeration;

import com.walterjwhite.logging.enumeration.LogLevel;
import com.walterjwhite.logging.plugin.advice.DebugAdvice;
import com.walterjwhite.logging.plugin.advice.InfoAdvice;
import com.walterjwhite.logging.plugin.advice.TraceAdvice;
import java.util.Arrays;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;

@Getter
@RequiredArgsConstructor
public enum GeneralType {
  Trace(LogLevel.TRACE, TraceAdvice.class) {
    @Override
    public boolean matches(MethodDescription methodDescription) {
      if (methodDescription.isPrivate()) {
        return true;
      }

      if (methodDescription.isConstructor()) {
        return true;
      }

      return isSetterOrGetterOrToStringOrHashCodeOrEquals(methodDescription);
    }

    public boolean isSetterOrGetterOrToStringOrHashCodeOrEquals(
        final MethodDescription methodDescription) {
      if (!methodDescription.isPublic()) {
        return false;
      }

      if (methodDescription.getName().startsWith("get")
          && methodDescription.getParameters().isEmpty()) {
        return true;
      }

      if (methodDescription.getName().startsWith("set")
          && methodDescription.getReturnType().equals(TypeDescription.Generic.VOID)) {
        return true;
      }

      if (methodDescription.getName().equals("toString")
          && methodDescription.getParameters().isEmpty()) {
        return true;
      }

      if (methodDescription.getName().equals("hashCode")
          && methodDescription.getParameters().isEmpty()) {
        return true;
      }

      return methodDescription.getName().equals("equals");
    }
  },
  Info(LogLevel.INFO, InfoAdvice.class) {
    @Override
    public boolean matches(MethodDescription methodDescription) {
      return methodDescription.isPublic();
    }
  },
  Debug(LogLevel.DEBUG, DebugAdvice.class) {
    @Override
    public boolean matches(MethodDescription methodDescription) {
      return true;
    }
  };

  private final LogLevel logLevel;
  private final Class adviceClass;

  public abstract boolean matches(final MethodDescription methodDescription);

  public static Optional<GeneralType> getAdvice(final MethodDescription methodDescription) {
    return Arrays.stream(values())
        .filter(generalType -> generalType.matches(methodDescription))
        .findFirst();
  }
}
