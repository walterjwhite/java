package com.walterjwhite.csv.plugins.index;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import org.apache.commons.csv.CSVRecord;

public class CSVRecordEntity extends AbstractEntity {
  // used to specify the index name in the index service
  protected final String csvFile;
  protected final CSVRecord csvRecord;

  public CSVRecordEntity(String csvFile, int row, CSVRecord csvRecord) {
    this.csvFile = csvFile;
    this.id = Integer.toString(row);
    this.csvRecord = csvRecord;
  }

  @Override
  public String toString() {
    return "CSVRecordEntity{" + "csvRecord=" + csvRecord + '}';
  }
}
