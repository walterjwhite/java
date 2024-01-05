package com.walterjwhite.datastore.query.entityType;

import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.query.AbstractSingularQuery;

public class FindEntityTypeByFilenameQuery extends AbstractSingularQuery<EntityType> {
  protected final String entityTypeName;

  public FindEntityTypeByFilenameQuery(String entityTypeName) {
    super(EntityType.class, false);
    this.entityTypeName = entityTypeName;
  }
}
