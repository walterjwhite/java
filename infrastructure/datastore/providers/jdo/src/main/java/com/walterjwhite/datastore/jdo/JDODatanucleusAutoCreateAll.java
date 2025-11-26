package com.walterjwhite.datastore.jdo;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface JDODatanucleusAutoCreateAll extends ConfigurableProperty {
  @DefaultValue boolean Default = true;
}
