package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.Optional;

@Optional
public interface JavaEnvironmentProperty extends ConfigurableProperty {
  String getKey();

  Class<? extends ConfigurableProperty> getPropertyKey();
}
