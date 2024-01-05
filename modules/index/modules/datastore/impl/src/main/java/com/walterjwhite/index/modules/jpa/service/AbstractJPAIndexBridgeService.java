package com.walterjwhite.index.modules.jpa.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.index.api.service.IndexBridgeService;
import lombok.RequiredArgsConstructor;

/**
 * @deprecated This class should live in the index project as a JPA module
 */
@RequiredArgsConstructor
public abstract class AbstractJPAIndexBridgeService
    implements IndexBridgeService<AbstractEntity, Class<? extends AbstractEntity>> {
  protected final Repository repository;

  @Override
  public AbstractEntity get(Class<? extends AbstractEntity> entityType, Integer id) {
    return (repository.findById(entityType, id));
  }
}
