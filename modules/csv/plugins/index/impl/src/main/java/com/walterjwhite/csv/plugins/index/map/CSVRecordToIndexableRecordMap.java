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

/** Maps a CSVRecord to an IndexableRecord (which can be ingested by the indexService) */
public class CSVRecordToIndexableRecordMap implements Function<CSVRecord, IndexableRecord> {
  //  protected final Provider<Repository> repositoryProvider;

  @Override
  public IndexableRecord apply(CSVRecord csvRecordData) {
    //    Repository repository = repositoryProvider.get();

    Index index = getIndex(csvRecordData);
    //        repository.query(new FindIndexByNameQuery(FindIndexByNameQuery.DEFAULT_INDEX_NAME) /*,
    //            PersistenceOption.Create*/);

    EntityType entityType =
        //        repository.query(new FindEntityTypeByFilenameQuery(csvRecordData.getCsvFilename())
        // /*,
        //            PersistenceOption.Create*/);
        getEntityType(csvRecordData);
    EntityReference entityReference =
        //        repository.query(
        //            new FindEntityReferenceByTypeAndIdQuery(entityType,
        // csvRecordData.getRowNumber()));
        getEntityReference(entityType, csvRecordData);

    return new IndexableRecord(index, entityReference, csvRecordData, null);

    // createCSVIndexRecord(csvRecordData));
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

  // once we know the entity reference, we will go through the bridge to get the underlying data ...
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
