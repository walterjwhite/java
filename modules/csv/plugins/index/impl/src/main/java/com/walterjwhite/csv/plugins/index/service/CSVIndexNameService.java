package com.walterjwhite.csv.plugins.index.service;

import com.walterjwhite.index.api.service.IndexNameService;
import jakarta.inject.Singleton;

@Singleton
public class CSVIndexNameService implements IndexNameService<String, Long> {
  @Override
  public String getIndexName(String filename) {
    return filename;
  }

  @Override
  public String getIndexName(Class<? extends String> entityTypeClass) {
    return null;
  }

  //  @Override
  //  public String getEntityType(String entityTypeString) {
  //    return entityTypeString;
  //  }

  @Override
  public Long getEntityId(String indexId) {
    return Long.valueOf(indexId);
  }

  @Override
  public String getIndexId(Long entityId) {
    return Long.toString(entityId);
  }
}
