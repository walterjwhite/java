package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;

public class FindAllQuery<EntityType extends AbstractEntity>
    extends AbstractQuery<EntityType, EntityType> {
  public FindAllQuery(final int offset, final int recordCount, Class<EntityType> entityTypeClass) {
    super(offset, recordCount, entityTypeClass, entityTypeClass, false);
  }
}
