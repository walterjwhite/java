package com.walterjwhite.encryption.impl;

import com.walterjwhite.logging.annotation.Sensitive;
import java.lang.reflect.Field;

public class FieldUtil {
  private FieldUtil() {}

  @Sensitive
  public static void setValue(final Object e, final Field targetField, final Object value)
      throws IllegalAccessException {
    final boolean targetFieldIsAccessible = targetField.isAccessible();

    targetField.setAccessible(true);
    targetField.set(e, value);
    targetField.setAccessible(targetFieldIsAccessible);
  }

  @Sensitive
  public static Object getValue(final Object e, final Field targetField)
      throws IllegalArgumentException, IllegalAccessException {
    final boolean targetFieldIsAccessible = targetField.isAccessible();

    targetField.setAccessible(true);
    final Object value = targetField.get(e);
    targetField.setAccessible(targetFieldIsAccessible);

    return (value);
  }

  public static String getSaltField(final Field field) {
    return (field.getName() + "Salt");
  }

  public static String getEncryptedField(final Field field) {
    return (field.getName() + "Encrypted");
  }
}
