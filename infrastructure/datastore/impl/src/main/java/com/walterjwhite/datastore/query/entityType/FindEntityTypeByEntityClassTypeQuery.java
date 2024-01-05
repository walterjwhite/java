package com.walterjwhite.datastore.query.entityType;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.datastore.query.AbstractSingularQuery;

public class FindEntityTypeByEntityClassTypeQuery extends AbstractSingularQuery<EntityType> {
  protected Class<? extends AbstractEntity> entityClassType;

  public FindEntityTypeByEntityClassTypeQuery(Class<? extends AbstractEntity> entityClassType) {
    super(EntityType.class, false);
    this.entityClassType = entityClassType;
  }
}
