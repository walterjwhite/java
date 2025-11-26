package com.walterjwhite.property.impl;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.reflect.Modifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class PropertyHelper {

  public static boolean isConcrete(final Class clazz) {
    return (!Modifier.isAbstract(clazz.getModifiers()));
  }

  public static boolean isOptional(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.isAnnotationPresent(Optional.class);
  }

  public static void validatePropertyConfiguration(
      final Class<? extends ConfigurableProperty> configurablePropertyClass, final String value) {
    if (!PropertyHelper.isOptional(configurablePropertyClass) && value == null) {
      LOGGER.warn("Required property is not set: {}", configurablePropertyClass);
    }
  }

  public static Class getPropertyValueType(
      Class<? extends ConfigurableProperty> configurableProperty) {
    if (configurableProperty.isAnnotationPresent(PropertyValueType.class)) {
      return configurableProperty.getAnnotation(PropertyValueType.class).value();
    }

    return String.class;
  }

  public static boolean isSensitive(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.isAnnotationPresent(Sensitive.class);
  }
}
