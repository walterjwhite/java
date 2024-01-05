package com.walterjwhite.csv.api.service.writer;

import java.io.IOException;

public interface CSVWriter extends AutoCloseable {
  // @NOTE: written when initialized
  //    void writeHeaders(final String[] headers);

  void writeRecord(final String[] record) throws IOException;
}
