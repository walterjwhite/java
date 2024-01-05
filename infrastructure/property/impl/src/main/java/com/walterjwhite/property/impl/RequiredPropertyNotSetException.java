package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.property.ConfigurableProperty;
import lombok.Getter;

@Getter
public class RequiredPropertyNotSetException extends IllegalStateException {
  protected final Class<? extends ConfigurableProperty> propertyClass;

  public RequiredPropertyNotSetException(
      final Class<? extends ConfigurableProperty> propertyClass) {
    super(
        propertyClass.getName()
            + " was not set and not default value was provided either, this is likely a"
            + " configuration problem.");
    this.propertyClass = propertyClass;
  }
}
