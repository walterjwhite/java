package com.walterjwhite.csv.api.legacy;

/** Filters the CSVRecord (String[]) input. ie. remove carriage returns, newlines, etc. */
public interface CSVRecordFilter {
  String[] filter(String[] in);
}
