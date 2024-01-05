package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public abstract class AbstractCountQuery<EntityType extends AbstractEntity>
    extends AbstractQuery<EntityType, Long> {
  public AbstractCountQuery(Class<EntityType> entityTypeClass /*,
      Predicate predicate*/) {
    super(0, 1, entityTypeClass, Long.class, false);
  }
}
