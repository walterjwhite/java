package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public enum TargetDatabase implements ConfigurableProperty {
  @DefaultValue
  H2,
  Derby,
  PostgresPlus,
  PostgreSQL,
  Oracle10g,
  SQLServer2008;
}
