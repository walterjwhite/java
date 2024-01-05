package com.walterjwhite.csv.modules.apache.reader;

import com.walterjwhite.csv.api.service.reader.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class ApacheCommonsCSVReader implements CSVReader {
  protected final CSVFormat csvFormat;
  protected final CSVParser csvParser;

  public ApacheCommonsCSVReader(CSVFormat csvFormat, final Reader reader) throws IOException {

    this.csvFormat = csvFormat;
    //        CSVFormat.DEFAULT
    //            .withIgnoreEmptyLines()
    //            .withTrim()
    //            //                .withHeader(columns)
    //            //                .withDelimiter(delimeter)
    //            .withFirstRecordAsHeader();
    csvParser = csvFormat.parse(reader);
  }

  @Override
  public Iterator<String[]> iterator() {
    return new ApacheCSVRecordIteratorWrapper(csvParser.iterator());
  }

  @Override
  public String[] getHeaders() {
    return null;
  }

  @Override
  public void close() throws Exception {
    csvParser.close();
  }
}
