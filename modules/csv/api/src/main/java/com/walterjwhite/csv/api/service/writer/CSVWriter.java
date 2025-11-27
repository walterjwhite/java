package com.walterjwhite.csv.api.service.writer;

import java.io.IOException;

public interface CSVWriter extends AutoCloseable {

  void writeRecord(final String[] record) throws IOException;
}
