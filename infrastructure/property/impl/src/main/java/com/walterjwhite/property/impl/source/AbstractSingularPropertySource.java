package com.walterjwhite.property.impl.source;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public abstract class AbstractSingularPropertySource<PropertyType extends ConfigurableProperty>
    extends AbstractPropertySource<PropertyType, String> {
  protected AbstractSingularPropertySource(
      PropertyManager propertyManager, Class<PropertyType> propertyTypeClass) {
    super(propertyManager, propertyTypeClass);
  }

  protected void doSet(Class<? extends PropertyType> propertyType, final String value) {
    propertyManager.set(propertyType, value);
  }
}
