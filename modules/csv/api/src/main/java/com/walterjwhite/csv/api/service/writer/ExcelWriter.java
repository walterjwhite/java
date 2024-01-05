package com.walterjwhite.csv.api.service.writer;

public interface ExcelWriter extends AutoCloseable {
  void writeRecord(String[] record);
}
