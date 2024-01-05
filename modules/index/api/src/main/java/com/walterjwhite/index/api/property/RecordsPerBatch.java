package com.walterjwhite.index.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface RecordsPerBatch extends ConfigurableProperty {
  @DefaultValue int Default = 500;
}
