package com.walterjwhite.csv.modules.apache.writer;

import com.walterjwhite.csv.api.service.writer.CSVWriter;
import com.walterjwhite.csv.api.service.writer.CSVWriterProducer;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;

public class ApacheCommonsCSVWriterProducer implements CSVWriterProducer {

  @Override
  public CSVWriter get(Appendable output, char delimeter, String... columns) throws IOException {
    return (new ApacheCommonsCSVWriter(
        CSVFormat.DEFAULT
            .withIgnoreEmptyLines()
            .withTrim()
            //            .withHeader(columns)
            .withDelimiter(delimeter),
        output));
  }
}
