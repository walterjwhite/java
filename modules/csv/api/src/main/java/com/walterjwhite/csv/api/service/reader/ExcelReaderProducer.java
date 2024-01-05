package com.walterjwhite.csv.api.service.reader;

import java.io.Reader;

@FunctionalInterface
public interface ExcelReaderProducer {
  ExcelReader get(final String tabName, Reader reader);
}
