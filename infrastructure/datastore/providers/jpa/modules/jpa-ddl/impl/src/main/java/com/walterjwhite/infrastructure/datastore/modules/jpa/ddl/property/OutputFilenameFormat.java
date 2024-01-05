package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface OutputFilenameFormat extends ConfigurableProperty {
  @DefaultValue String Default = "%s-DDL-%s-%s.sql";
}
