package com.walterjwhite.datastore.jdo;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface PersistenceUnitName extends ConfigurableProperty {
  @DefaultValue String DEFAULT = "default";
}
