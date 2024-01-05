package com.walterjwhite.index.api.service;

import java.io.Serializable;

// originally built to provide a means to determine an index's name
// ie. if we index a CSV file, the index name might be the filename
// ie. if we index a database table, the index name might be the schema name.table name
public interface IndexNameService<EntityType extends Serializable, EntityId extends Serializable> {
  EntityId getEntityId(final String indexId);

  String getIndexId(final EntityId entityId);

  String getIndexName(EntityType entityType);

  String getIndexName(Class<? extends EntityType> entityTypeClass);

  //  EntityType getEntityType(final String indexName);
}
