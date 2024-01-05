package com.walterjwhite.property.modules.system;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.JavaEnvironmentProperty;
import com.walterjwhite.property.impl.tgt.AbstractPropertyTarget;
import java.util.Arrays;

public class MappedJavaPropertiesPropertyTarget
    extends AbstractPropertyTarget<JavaEnvironmentProperty> {
  public MappedJavaPropertiesPropertyTarget(PropertyManager propertyManager) {
    super(propertyManager, JavaEnvironmentProperty.class);
  }

  @Override
  protected void set(
      Class<? extends JavaEnvironmentProperty> javaEnvironmentProperty, final String value) {}

  @Override
  protected void set(Class<? extends JavaEnvironmentProperty> property) {
    Arrays.stream(property.getEnumConstants()).forEach(constant -> setProperty(constant));
  }

  protected void setProperty(final JavaEnvironmentProperty javaEnvironmentProperty) {
    final String value = propertyManager.get(javaEnvironmentProperty.getPropertyKey());
    if (value != null) System.setProperty(javaEnvironmentProperty.getKey(), value);
  }
}
