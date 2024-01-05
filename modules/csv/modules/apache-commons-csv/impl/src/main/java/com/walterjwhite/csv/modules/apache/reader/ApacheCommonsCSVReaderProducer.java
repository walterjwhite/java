package com.walterjwhite.csv.modules.apache.reader;

import com.walterjwhite.csv.api.service.reader.CSVReader;
import com.walterjwhite.csv.api.service.reader.CSVReaderProducer;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;

public class ApacheCommonsCSVReaderProducer implements CSVReaderProducer {

  @Override
  public CSVReader get(Reader reader, char delimeter, String... columns) throws IOException {
    return (new ApacheCommonsCSVReader(
        CSVFormat.DEFAULT
            .withIgnoreEmptyLines()
            .withTrim()
            //            .withHeader(columns)
            .withDelimiter(delimeter),
        reader));
  }
}
