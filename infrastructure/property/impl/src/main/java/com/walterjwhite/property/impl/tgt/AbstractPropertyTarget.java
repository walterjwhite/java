package com.walterjwhite.property.impl.tgt;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.tgt.PropertyTarget;

public abstract class AbstractPropertyTarget<PropertyType extends ConfigurableProperty>
    implements PropertyTarget {
  protected final PropertyManager propertyManager;
  protected final Class<? extends PropertyType> propertyTypeClass;

  protected AbstractPropertyTarget(
      PropertyManager propertyManager, Class<PropertyType> propertyTypeClass) {
    this.propertyManager = propertyManager;
    this.propertyTypeClass = propertyTypeClass;
  }

  @Override
  public void set() {
    propertyManager.getReflections().getSubTypesOf(propertyTypeClass).forEach(p -> set(p));
  }

  protected void set(Class<? extends PropertyType> property) {
    final String value = propertyManager.get(property);

    if (value != null) set(property, value);
  }

  protected abstract void set(Class<? extends PropertyType> property, final String value);
}
