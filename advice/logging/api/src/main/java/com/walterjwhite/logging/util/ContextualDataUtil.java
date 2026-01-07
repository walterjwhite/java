package com.walterjwhite.logging.util;

import com.walterjwhite.logging.FieldContextualLoggable;
import com.walterjwhite.logging.annotation.ContextualLoggableField;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContextualDataUtil {
  public static String getContextualData(final Object target) {
    final StringBuilder buffer = new StringBuilder();
    getContextual(target)
        .entrySet()
        .forEach(
            entry ->
                buffer
                    .append(entry.getKey())
                    .append("->")
                    .append(ArgumentUtil.toLoggableString(-1, entry.getValue())));

    return buffer.toString();
  }

  public static Map<String, Object> getContextual(final Object target) {
    final Map<String, Object> contextualData = new HashMap<>();
    getContextual(contextualData, target, target.getClass());

    return contextualData;
  }

  private static void getContextual(
      final Map<String, Object> contextual, final Object object, final Class targetClass) {
    for (final Field field : targetClass.getDeclaredFields()) {
      if (field.isAnnotationPresent(ContextualLoggableField.class)) {
        try {
          final boolean wasAccessible = field.canAccess(object);
          field.setAccessible(true);

          contextual.put(targetClass.getName() + "." + field.getName(), field.get(object));

          field.setAccessible(wasAccessible);
        } catch (Exception e) {
        }
      }
    }

    if (targetClass.getSuperclass() != null
        && targetClass.getSuperclass().isAssignableFrom(FieldContextualLoggable.class)) {
      getContextual(contextual, object, targetClass.getSuperclass());
    }
  }
}
