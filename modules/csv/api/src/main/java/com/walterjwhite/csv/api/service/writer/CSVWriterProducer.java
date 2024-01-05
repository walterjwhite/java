package com.walterjwhite.csv.api.service.writer;

import java.io.IOException;

@FunctionalInterface
public interface CSVWriterProducer {
  default CSVWriter get(Appendable appendable, final String... columns) throws IOException {
    return (get(appendable, ',', columns));
  }

  CSVWriter get(Appendable appendable, final char delimeter, final String... columns)
      throws IOException;
}
