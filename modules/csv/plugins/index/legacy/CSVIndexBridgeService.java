package com.walterjwhite.csv.plugins.index.legacy;

import com.walterjwhite.csv.api.legacy.CSVParser;
import com.walterjwhite.csv.api.legacy.CSVParserProducer;
import com.walterjwhite.csv.plugins.index.model.CSVIndexRecord;
import com.walterjwhite.index.api.service.IndexBridgeService;
import java.io.*;
import jakarta.inject.Inject;

// At this point, the file is indexed, and we are trying to get a row out of the CSV file.
// It would be much more efficient to also push JDBC
// Lastly, can we not get the source record back out of ElasticSearch (then translate it back to a
// CSVIndexRecord)?
public class CSVIndexBridgeService implements IndexBridgeService<CSVIndexRecord, String, Long> {
  protected final CSVParserProducer csvParserProducer;

  @Inject
  public CSVIndexBridgeService(CSVParserProducer csvParserProducer) {

    this.csvParserProducer = csvParserProducer;
  }

  @Override
  public CSVIndexRecord get(String filename, Long id) throws IOException {
    final CSVParser csvParser =
        csvParserProducer.get(
            filename,
            new InputStreamReader(
                new BufferedInputStream(new FileInputStream(new File(filename)))));
    final String[] csvRecordData = csvParser.getRecordAt(id);

    return IndexingRecordReader.createCSVIndexRecord(
        filename, csvParser.getHeaders(), csvRecordData);
  }
}
