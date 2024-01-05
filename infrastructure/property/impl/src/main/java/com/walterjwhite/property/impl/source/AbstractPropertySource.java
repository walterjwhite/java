package com.walterjwhite.property.impl.source;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.source.PropertySource;

public abstract class AbstractPropertySource<PropertyType extends ConfigurableProperty, ValueType>
    implements PropertySource {
  protected final PropertyManager propertyManager;
  protected final Class<PropertyType> propertyTypeClass;

  protected AbstractPropertySource(
      PropertyManager propertyManager, Class<PropertyType> propertyTypeClass) {
    this.propertyManager = propertyManager;
    this.propertyTypeClass = propertyTypeClass;
  }

  @Override
  public void get() {
    propertyManager
        .getReflections()
        .getSubTypesOf(propertyTypeClass)
        .forEach(p -> doSet(p, get(p)));
  }

  protected abstract ValueType get(Class<? extends PropertyType> property);

  protected abstract void doSet(Class<? extends PropertyType> propertyType, final ValueType value);

  protected String getName(Class<? extends PropertyType> propertyType) {
    return propertyManager.lookup(propertyType);
  }
}
