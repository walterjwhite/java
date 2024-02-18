package com.walterjwhite.csv.modules.apache.reader;

import java.util.Iterator;
import org.apache.commons.csv.CSVRecord;

public class ApacheCSVRecordIteratorWrapper implements Iterator<String[]> {
  protected final Iterator<CSVRecord> csvRecordIterator;

  public ApacheCSVRecordIteratorWrapper(Iterator<CSVRecord> csvRecordIterator) {

    this.csvRecordIterator = csvRecordIterator;
  }

  @Override
  public boolean hasNext() {
    return csvRecordIterator.hasNext();
  }

  @Override
  public String[] next() {
    final CSVRecord csvRecord = csvRecordIterator.next();
    final String[] row = new String[csvRecord.size()];
    for (int i = 0; i < csvRecord.size(); i++) row[i] = csvRecord.get(i);

    return (row);
  }
}
