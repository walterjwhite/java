package com.walterjwhite.property.impl.source;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public abstract class AbstractSingularStringPropertySource<
        PropertyType extends ConfigurableProperty>
    extends AbstractSingularPropertySource<PropertyType> {
  public AbstractSingularStringPropertySource(
      PropertyManager propertyManager, Class<PropertyType> propertyTypeClass) {
    super(propertyManager, propertyTypeClass);
  }

  protected String get(Class<? extends PropertyType> propertyType) {
    return doGet(getName(propertyType));
  }

  protected abstract String doGet(final String propertyKey);
}
