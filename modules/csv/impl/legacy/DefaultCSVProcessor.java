package com.walterjwhite.csv.impl.service;

import com.walterjwhite.csv.api.legacy.CSVParser;
import com.walterjwhite.csv.api.legacy.CSVProcessor;
import com.walterjwhite.csv.api.legacy.CSVRecordFilter;
import com.walterjwhite.csv.api.legacy.CSVRecordReader;

/**
 * TODO: migrate back to making the CSV processor multi-threaded. spawn off multiple record readers
 * and read through the CSV. 1. inject a csv record reader provider 2. run reader in a new thread 3.
 * limit number of threads to fixed number (configured beforehand) 4. block until there are more
 * available threads
 */
public class DefaultCSVProcessor implements CSVProcessor {
  @Override
  public void process(
      CSVParser csvParser, CSVRecordReader csvRecordReader, CSVRecordFilter... csvRecordFilters)
      throws Exception {
    doProcess(csvParser, csvRecordReader, csvRecordFilters);
  }

  protected void doProcess(
      final CSVParser csvParser,
      CSVRecordReader csvRecordReader,
      CSVRecordFilter... csvRecordFilters)
      throws Exception {
    String[] csvRecord;
    while ((csvRecord = csvParser.next()) != null) {

      final String[] filteredCSVRecord = doFilter(csvRecord, csvRecordFilters);

      if (filteredCSVRecord != null) {
        csvRecordReader.process(
            csvParser.getFilename(),
            csvParser.getHeaders(),
            csvParser.getCurrentRowNumber(),
            filteredCSVRecord);
      }
    }
  }

  protected String[] doFilter(String[] csvRecord, CSVRecordFilter... csvRecordFilters) {
    if (csvRecordFilters == null || csvRecordFilters.length == 0) return (csvRecord);

    for (CSVRecordFilter csvRecordFilter : csvRecordFilters) {
      csvRecord = csvRecordFilter.filter(csvRecord);
    }

    return (csvRecord);
  }
}
