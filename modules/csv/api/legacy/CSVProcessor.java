package com.walterjwhite.csv.api.legacy;

import com.walterjwhite.csv.api.parser.CSVParser;

public interface CSVProcessor {
  void process(
      CSVParser csvParser, CSVRecordReader csvRecordReader, CSVRecordFilter... csvRecordFilters)
      throws Exception;
}
