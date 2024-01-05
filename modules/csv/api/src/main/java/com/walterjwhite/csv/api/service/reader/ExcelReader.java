package com.walterjwhite.csv.api.service.reader;

import java.util.Iterator;

public interface ExcelReader extends AutoCloseable {
  Iterator<String[]> iterator();

  String[] getHeaders();
}
