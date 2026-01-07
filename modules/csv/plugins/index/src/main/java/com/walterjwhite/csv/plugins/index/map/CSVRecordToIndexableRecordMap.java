package com.walterjwhite.csv.plugins.index.map;

import com.walterjwhite.csv.api.model.CSVRecord;
import com.walterjwhite.csv.plugins.index.model.CSVIndexRecord;
import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityReference;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CSVRecordToIndexableRecordMap implements Function<CSVRecord, IndexableRecord> {

  @Override
  public IndexableRecord apply(CSVRecord csvRecordData) {

    Index index = getIndex(csvRecordData);

    EntityType entityType =
        getEntityType(csvRecordData);
    EntityReference entityReference =
        getEntityReference(entityType, csvRecordData);

    return new IndexableRecord(index, entityReference, csvRecordData, null);

  }

  protected Index getIndex(final CSVRecord csvRecord) {
    return new Index(csvRecord.getId().getCsvFilename());
  }

  protected EntityType getEntityType(final CSVRecord csvRecord) {
    return new EntityType(csvRecord.getId().getCsvFilename(), EntityContainerType.File);
  }

  protected EntityReference getEntityReference(
      final EntityType entityType, final CSVRecord csvRecord) {
    return new EntityReference(entityType, csvRecord.getId().getRowNumber());
  }

  public static CSVIndexRecord createCSVIndexRecord(
      /*final String csvFilename, final String[] columnNames, final String[] csvRecordData*/
      CSVRecord csvRecord) {
    return (new CSVIndexRecord(
        csvRecord.getId().getCsvFilename(),
        getMap(csvRecord.getColumNames(), csvRecord.getData())));
  }

  protected static Map<String, String> getMap(final String[] columnNames, String[] csvRecordData) {
    final Map<String, String> map = new HashMap<>();
    int i = 0;
    for (final String columnName : columnNames) {
      map.put(columnName, csvRecordData[i++]);
    }

    return (map);
  }
}
