package com.walterjwhite.property.modules.system;

import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.impl.tgt.AbstractPropertyTarget;

public class SystemPropertyTarget extends AbstractPropertyTarget<ConfigurableProperty> {
  public SystemPropertyTarget(PropertyManager propertyManager) {
    super(propertyManager, ConfigurableProperty.class);
  }

  @Override
  protected void set(Class<? extends ConfigurableProperty> property, final String value) {
    System.setProperty(propertyManager.lookup(property), value);
  }
}
