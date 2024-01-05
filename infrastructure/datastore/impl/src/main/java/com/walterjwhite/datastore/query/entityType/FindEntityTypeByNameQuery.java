package com.walterjwhite.datastore.query.entityType;

import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.query.AbstractSingularQuery;
import lombok.Getter;

@Getter
public class FindEntityTypeByNameQuery extends AbstractSingularQuery<EntityType> {
  protected final String entityTypeName;

  public FindEntityTypeByNameQuery(String entityTypeName) {
    super(EntityType.class, true);
    this.entityTypeName = entityTypeName;
  }
}
