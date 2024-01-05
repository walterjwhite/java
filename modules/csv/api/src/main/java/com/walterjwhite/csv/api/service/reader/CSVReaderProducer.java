package com.walterjwhite.csv.api.service.reader;

import java.io.IOException;
import java.io.Reader;

@FunctionalInterface
public interface CSVReaderProducer {
  default CSVReader get(Reader reader, final String... columns) throws IOException {
    return (get(reader, ',', columns));
  }

  CSVReader get(Reader reader, final char delimeter, final String... columns) throws IOException;
}
