package com.walterjwhite.infrastructure.datastore.modules.criteria.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.query.AbstractQuery;

public class GetQuery<EntityType extends AbstractEntity>
    extends AbstractQuery<EntityType, EntityType> {
  protected final String id;

  public GetQuery(Class<EntityType> entityTypeClass, String id) {
    super(0, 1, entityTypeClass, entityTypeClass, false);
    this.id = id;
  }
}
