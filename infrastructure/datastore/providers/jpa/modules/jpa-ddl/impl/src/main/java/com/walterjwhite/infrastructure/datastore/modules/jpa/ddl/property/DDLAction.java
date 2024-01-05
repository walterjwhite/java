package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public enum DDLAction implements ConfigurableProperty {
  @DefaultValue
  CREATE(SchemaExport.Action.CREATE),
  DROP(SchemaExport.Action.DROP),
  BOTH(SchemaExport.Action.BOTH);

  private final SchemaExport.Action action;

  DDLAction(SchemaExport.Action action) {
    this.action = action;
  }

  public SchemaExport.Action getAction() {
    return action;
  }
}
