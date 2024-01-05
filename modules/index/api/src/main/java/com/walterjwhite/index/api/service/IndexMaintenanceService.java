package com.walterjwhite.index.api.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.io.IOException;

public interface IndexMaintenanceService {
  void create(Class<? extends AbstractEntity> entityClass) throws IOException;

  void delete(Class<? extends AbstractEntity> entityClass) throws IOException;
}
