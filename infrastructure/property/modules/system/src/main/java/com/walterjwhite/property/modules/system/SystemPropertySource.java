package com.walterjwhite.property.modules.system;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.source.AbstractSingularStringPropertySource;

@PropertySourceIndex(5)
public class SystemPropertySource
    extends AbstractSingularStringPropertySource<ConfigurableProperty> {
  public SystemPropertySource(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  @Override
  protected String doGet(String propertyKey) {
    return System.getProperty(propertyKey);
  }
}
