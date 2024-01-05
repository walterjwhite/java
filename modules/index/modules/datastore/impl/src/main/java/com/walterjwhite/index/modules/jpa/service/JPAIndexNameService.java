package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.service.IndexNameService;

/**
 * @deprecated This class should live in the index project as a JPA module
 */
public class JPAIndexNameService implements IndexNameService<AbstractEntity, String> {

  @Override
  public String getIndexName(AbstractEntity entityClass) {
    return getIndexName(entityClass.getClass());
  }

  @Override
  public String getIndexName(Class<? extends AbstractEntity> entityTypeClass) {
    return entityTypeClass.getName().toLowerCase();
  }

  //  @Override
  //  public Class<? extends AbstractEntity> getEntityType(String entityTypeString) {
  //    try {
  //      return (Class<? extends AbstractEntity>) Class.forName(entityTypeString);
  //    } catch (ClassNotFoundException e) {
  //      throw new IllegalStateException("Unable to map entity class", e);
  //    }
  //  }

  @Override
  public String getEntityId(String indexId) {
    return indexId;
  }

  @Override
  public String getIndexId(String entityId) {
    return entityId;
  }
}
