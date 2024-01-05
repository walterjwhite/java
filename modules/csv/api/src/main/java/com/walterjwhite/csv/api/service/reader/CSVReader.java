package com.walterjwhite.csv.api.service.reader;

import java.util.Iterator;

public interface CSVReader extends AutoCloseable {
  Iterator<String[]> iterator();

  String[] getHeaders();
}
