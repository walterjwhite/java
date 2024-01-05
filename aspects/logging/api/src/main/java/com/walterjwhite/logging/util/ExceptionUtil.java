package com.walterjwhite.logging.util;

import com.walterjwhite.logging.ContextualLoggable;
import com.walterjwhite.logging.enumeration.LogLevel;
import java.lang.ref.WeakReference;
import java.security.SecureRandom;
import java.util.Base64;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {
  private static final ThreadLocal<WeakReference<Integer>> EXCEPTION_REFERENCE =
      new ThreadLocal<>();

  public static final SecureRandom random = new SecureRandom();
  public static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
  public static int idLength = 8;

  public static void onException(
      final ContextualLoggable contextualLoggable,
      final String typeName,
      final String methodName,
      final Object[] arguments,
      final Throwable throwable) {
    onException(typeName, methodName, arguments, throwable);

    // print contextual information
    LogLevel.ERROR.log(
        LoggerFactory.getLogger(typeName),
        "Contextual Details:\n{}",
        contextualLoggable.printContextualInformation());
  }

  public static void onException(
      final String typeName,
      final String methodName,
      final Object[] arguments,
      final Throwable throwable) {
    if (wasAlreadyLogged(throwable)) {
      return;
    }

    EXCEPTION_REFERENCE.set(new WeakReference<>(throwable.hashCode()));

    LogLevel.ERROR.log(
        LoggerFactory.getLogger(typeName),
        "{}({}){}",
        methodName,
        arguments,
        getStackTrace(throwable, getExceptionId()));
  }

  private static boolean wasAlreadyLogged(final Throwable throwable) {
    if (EXCEPTION_REFERENCE.get() == null) {
      return false;
    }
    if (EXCEPTION_REFERENCE.get().get() == null) {
      return false;
    }

    return EXCEPTION_REFERENCE.get().get().equals(throwable.hashCode());
  }

  public static String getExceptionId() {
    byte[] buffer = new byte[idLength];
    random.nextBytes(buffer);
    return encoder.encodeToString(buffer);
  }

  public static String getStackTrace(final Throwable throwable, final String exceptionId) {
    final StringBuilder buffer = new StringBuilder();

    buffer.append(" - " + exceptionId + "\n");

    buffer.append(throwable.getClass());

    if (throwable.getMessage() != null) {
      buffer.append(":");
      buffer.append(throwable.getMessage());
      buffer.append("\n");
    }

    if (throwable.getCause() != null) {
      buffer.append(throwable.getCause().getMessage());
      buffer.append("\n");
    }

    for (final StackTraceElement stackTraceElement : throwable.getStackTrace()) {
      buffer.append("\t" + stackTraceElement.toString() + "\n");
    }

    return buffer.toString();
  }
}
