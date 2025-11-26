package com.walterjwhite.csv.modules.univocity.reader;

import com.univocity.parsers.csv.CsvParser;
import com.walterjwhite.csv.api.service.reader.CSVReader;
import java.util.Iterator;

public class UnivocityCSVReader implements CSVReader {
  protected int currentRowNumber = 0;
  protected final String[] columns;
  protected final CsvParser csvParser;

  public UnivocityCSVReader(CsvParser csvParser, final String... columns) {

    this.csvParser = csvParser;
    this.columns = columns;
  }


  @Override
  public Iterator<String[]> iterator() {
    return (null);
  }

  @Override
  public String[] getHeaders() {
    return /*columns;*/ csvParser.getContext().parsedHeaders();
  }


  @Override
  public void close() {}

}
