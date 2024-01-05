package com.walterjwhite.csv.api.legacy;

import java.io.IOException;

public interface CSVParser {
  String[] next() throws IOException;

  String[] getHeaders();

  String getFilename();

  int getCurrentRowNumber();

  String[] getRecordAt(final long rowNumber) throws IOException;

  //  Iterator<String[]> iterator();
}
