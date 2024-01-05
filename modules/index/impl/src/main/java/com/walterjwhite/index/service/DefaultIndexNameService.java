package com.walterjwhite.index.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.service.IndexNameService;

public class DefaultIndexNameService implements IndexNameService<AbstractEntity, String> {
  @Override
  public String getIndexName(AbstractEntity entity) {
    return getIndexName(entity.getClass());
  }

  @Override
  public String getIndexName(Class<? extends AbstractEntity> entityTypeClass) {
    return entityTypeClass.getName().toLowerCase();
  }

  //  @Override
  //  public Class getEntityType(String entityType) {
  //    try {
  //      return Class.forName(entityType);
  //    } catch (ClassNotFoundException e) {
  //      throw new RuntimeException("Error getting class from: " + entityType);
  //    }
  //  }

  @Override
  public String getEntityId(String indexId) {
    return indexId;
  }

  @Override
  public String getIndexId(String id) {
    /*return String.valueOf(serializable.hashCode());*/
    return id;
  }
}
