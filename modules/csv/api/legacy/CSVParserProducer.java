package com.walterjwhite.csv.api.legacy;

import java.io.Reader;

public interface CSVParserProducer {
  CSVParser get(String name, Reader reader, final String... columns);

  CSVParser get(String name, Reader reader, final char delimeter, final String... columns);
}
