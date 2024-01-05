package com.walterjwhite.index.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.service.IndexMaintenanceService;
import com.walterjwhite.index.api.service.IndexNameService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractIndexMaintenanceService implements IndexMaintenanceService {
  protected final IndexNameService indexNameService;

  @Override
  public void create(Class<? extends AbstractEntity> entityClass) throws IOException {
    doCreate(getIndex(entityClass));
  }

  protected abstract void doCreate(Index index) throws IOException;

  @Override
  public void delete(Class<? extends AbstractEntity> entityClass) throws IOException {
    doDelete(getIndex(entityClass));
  }

  protected Index getIndex(Class<? extends AbstractEntity> entityClass) {
    return new Index(indexNameService.getIndexName(entityClass));
  }

  protected abstract void doDelete(Index index) throws IOException;
}
