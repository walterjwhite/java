package com.walterjwhite.property.modules.defaultvalue;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularPropertySource;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

@PropertySourceIndex(0)
public class DefaultPropertySource extends AbstractSingularPropertySource<ConfigurableProperty> {
  public DefaultPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  @Override
  protected String get(Class<? extends ConfigurableProperty> configurableProperty) {
    for (final Field field : configurableProperty.getDeclaredFields()) {
      if (isDefault(field)) {
        final String defaultValue = getStringValue(field);
        handleEnum(configurableProperty, field, defaultValue);

        return defaultValue;
      }
    }

    return null;
  }

  protected String getStringValue(final Field field) {
    final Object fieldValue = getValue(field);
    if (Enum.class.isAssignableFrom(fieldValue.getClass())) {
      return ((Enum) fieldValue).name();
    }

    return fieldValue.toString();
  }

  protected boolean isDefault(final Field field) {
    return field.isAnnotationPresent(DefaultValue.class);
  }

  protected boolean handleEnum(
      Class<? extends ConfigurableProperty> configurableProperty,
      final Field field,
      final String defaultValue) {
    if (isEnum(field)) {
      handleInterfaces(configurableProperty, defaultValue);
      return true;
    }

    return false;
  }

  protected Object getValue(final Field field) {
    final boolean wasAccessible = field.isAccessible();
    try {
      field.setAccessible(true);
      return (field.get(null));
    } catch (IllegalAccessException e) {
      throw new RuntimeException("Error getting value:", e);
    } finally {
      field.setAccessible(wasAccessible);
    }
  }

  protected boolean isEnum(final Field field) {
    return field.isEnumConstant();
  }

  protected Set<Class<? extends ConfigurableProperty>> handleInterfaces(
      final Class<? extends ConfigurableProperty> configurableProperty, final String defaultValue) {
    final Set<Class<? extends ConfigurableProperty>> interfaces = new HashSet<>();
    for (final Class<? extends ConfigurableProperty> configurablePropertyClass :
        ((Class<? extends ConfigurableProperty>[]) configurableProperty.getInterfaces())) {
      if (handleInterface(configurablePropertyClass, defaultValue)) {
        interfaces.add(configurablePropertyClass);
      }
    }

    return interfaces;
  }

  protected boolean handleInterface(
      final Class<? extends ConfigurableProperty> configurablePropertyClass,
      final String defaultValue) {
    if (!ConfigurableProperty.class.equals(configurablePropertyClass)) {
      propertyManager.set(configurablePropertyClass, defaultValue);
      return true;
    }

    return false;
  }
}
