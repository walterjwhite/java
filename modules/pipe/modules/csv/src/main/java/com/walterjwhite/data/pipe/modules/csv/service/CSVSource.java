package com.walterjwhite.data.pipe.modules.csv.service;

import com.walterjwhite.csv.api.service.reader.CSVReader;
import com.walterjwhite.csv.api.service.reader.CSVReaderProducer;
import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.data.pipe.modules.csv.model.CSVSourceConfiguration;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import jakarta.inject.Inject;

public class CSVSource extends AbstractSource<String[], CSVSourceConfiguration> {
  protected final CSVReaderProducer csvReaderProducer;

  protected CSVReader csvReader;

  @Inject
  public CSVSource(CSVReaderProducer csvReaderProducer) {

    this.csvReaderProducer = csvReaderProducer;
  }

  @Override
  public Iterator<String[]> iterator() {
    return csvReader.iterator();
  }

  @Override
  public void close() {
    try {
      csvReader.close();
    } catch (Exception e) {
      throw new RuntimeException("Error closing csv source", e);
    }
  }

  @Override
  protected void doConfigure() {
    try {
      csvReader =
          csvReaderProducer.get(
              new FileReader(new File(sourceConfiguration.getCsvConfiguration().getFilename())),
              sourceConfiguration.getCsvConfiguration().getHeaders());
    } catch (IOException e) {
      throw new RuntimeException("Error configuring", e);
    }
  }
}
