package com.walterjwhite.csv.plugins.index.service;

import com.walterjwhite.csv.plugins.index.model.CSVIndexRecord;
import com.walterjwhite.serialization.api.service.JSON;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

public class CSVSerializerService
    implements JSON /*<CSVIndexRecord, Class<? extends CSVIndexRecord>>*/ {


  @Override
  public void serialize(Serializable data, OutputStream outputStream) {}

  @Override
  public CSVIndexRecord deserialize(InputStream inputStream) {
    throw new UnsupportedOperationException("not implemented");
  }

  @Override
  public <EntityType extends Serializable> EntityType deserialize(
      InputStream inputStream, Class<EntityType> entityType) {
    return null;
  }
}
