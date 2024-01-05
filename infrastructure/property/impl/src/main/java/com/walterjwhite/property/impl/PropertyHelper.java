package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import java.lang.reflect.Modifier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyHelper {

  // SEE: ServiceManager, make this a utility class / function
  public static boolean isConcrete(final Class clazz) {
    return (!Modifier.isAbstract(clazz.getModifiers()));
  }

  public static boolean isOptional(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    return configurablePropertyClass.isAnnotationPresent(Optional.class);
  }

  public static boolean isValidClass(
      final Class<? extends ConfigurableProperty> configurablePropertyClass) {
    try {
      Class.forName(configurablePropertyClass.getName());
      return true;
    } catch (ClassNotFoundException e) {
      return false;
    }
  }

  public static void validatePropertyConfiguration(
      final Class<? extends ConfigurableProperty> configurablePropertyClass, final String value) {
    if (value != null) {
      if (!PropertyHelper.isOptional(configurablePropertyClass)) {
        if (!PropertyHelper.isValidClass(configurablePropertyClass))
          throw new RequiredPropertyNotSetException(configurablePropertyClass);
      } else {
        // set optional ...
      }
    }
  }
}
