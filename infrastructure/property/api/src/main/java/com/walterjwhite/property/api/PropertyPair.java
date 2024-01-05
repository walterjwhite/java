package com.walterjwhite.property.api;

import com.walterjwhite.property.api.property.ConfigurableProperty;

public class PropertyPair {
  protected final Class<? extends ConfigurableProperty> configurablePropertyClass;
  protected final String value;

  public PropertyPair(
      Class<? extends ConfigurableProperty> configurablePropertyClass, String value) {
    this.configurablePropertyClass = configurablePropertyClass;
    this.value = value;
  }

  public Class<? extends ConfigurableProperty> getConfigurablePropertyClass() {
    return configurablePropertyClass;
  }

  public String getValue() {
    return value;
  }
}
