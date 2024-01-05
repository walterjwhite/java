package com.walterjwhite.csv.plugins.index.service;

import com.walterjwhite.csv.plugins.index.model.CSVIndexRecord;
import com.walterjwhite.serialization.api.service.JSON;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

// this is commented out because:
// 1. it isn't used
// 2. I changed the serialization service signature - 2019/08/24 and this doesn't fit that model
// (easily, within 1 minute of effort)
/** Simply takes a String of key-value pairs and converts them into a JSON object. */
public class CSVSerializerService
    implements JSON /*<CSVIndexRecord, Class<? extends CSVIndexRecord>>*/ {

  //  @Override
  //  public void serialize(CSVIndexRecord data, OutputStream outputStream) throws Exception {
  //    outputStream.write("{".getBytes(Charset.defaultCharset()));
  //    int i = 0;
  //    for (final String columnName : data.getValues().keySet()) {
  //      outputStream.write(
  //          ("\"" + columnName + "\": \"" + data.getValues().get(columnName) + "\"")
  //              .getBytes(Charset.defaultCharset()));
  //
  //      if (i < data.getValues().size() - 1)
  //        outputStream.write(",".getBytes(Charset.defaultCharset()));
  //      i++;
  //    }
  //
  //    outputStream.write("}".getBytes(Charset.defaultCharset()));
  //  }
  //  @Override
  //  public CSVIndexRecord deserialize(
  //      InputStream inputStream, Class<? extends CSVIndexRecord> entityType) throws IOException {
  //    throw new UnsupportedOperationException("not implemented"));
  //  }

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
