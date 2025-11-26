package com.walterjwhite.index.api.service;

import java.io.Serializable;

public interface IndexNameService<EntityType extends Serializable, EntityId extends Serializable> {
  EntityId getEntityId(final String indexId);

  String getIndexId(final EntityId entityId);

  String getIndexName(EntityType entityType);

  String getIndexName(Class<? extends EntityType> entityTypeClass);

}
