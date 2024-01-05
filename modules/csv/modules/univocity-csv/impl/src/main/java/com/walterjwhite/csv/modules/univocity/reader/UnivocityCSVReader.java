package com.walterjwhite.csv.modules.univocity.reader;

import com.univocity.parsers.csv.CsvParser;
import com.walterjwhite.csv.api.service.reader.CSVReader;
import java.util.Iterator;

public class UnivocityCSVReader implements CSVReader {
  protected int currentRowNumber = 0;
  //  protected final String name;
  protected final String[] columns;
  protected final CsvParser csvParser;

  public UnivocityCSVReader(CsvParser csvParser, final String... columns) {

    this.csvParser = csvParser;
    //    this.name = name;
    this.columns = columns;
  }

  //  @Override
  //  public String[] next() throws IOException {
  //    currentRowNumber++;
  //    return csvParser.parseNext();
  //  }

  @Override
  public Iterator<String[]> iterator() {
    //    return csvParser.;
    return (null);
  }

  @Override
  public String[] getHeaders() {
    return /*columns;*/ csvParser.getContext().parsedHeaders();
  }

  //  @Override
  //  public String getFilename() {
  //    return name;
  //  }
  //
  //  @Override
  //  public int getCurrentRowNumber() {
  //    return currentRowNumber;
  //  }
  //
  //  @Override
  //  public String[] getRecordAt(long rowNumber) throws IOException {
  //    if (rowNumber < 1) throw new IllegalArgumentException(rowNumber + " is < 0."));
  //
  //    if (currentRowNumber > 1 && currentRowNumber > rowNumber) {
  //      //      csvParser.stopParsing();
  //      //csvParser.getContext().
  //      throw new IllegalStateException("CSV parser is already advanced."));
  //    }
  //
  //    String[] record = null;
  //    while (currentRowNumber < rowNumber) {
  //      record = next();
  //    }
  //
  //    if (record == null) throw new IllegalStateException("No data found for selected row."));
  //
  //    return record;
  //  }

  @Override
  public void close() {}

  //
  //  @Override
  //  public Iterator<String[]> iterator() {
  //    return null;
  //  }
}
