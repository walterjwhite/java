package com.walterjwhite.csv.api.legacy;

public interface CSVRecordReader {

  void process(final String name, final String[] columnNames, final int rowNumber, String[] data)
      throws Exception;
}
