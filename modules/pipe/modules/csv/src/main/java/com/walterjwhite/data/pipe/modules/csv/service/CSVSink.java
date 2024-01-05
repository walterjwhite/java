package com.walterjwhite.data.pipe.modules.csv.service;

import com.walterjwhite.csv.api.service.writer.CSVWriter;
import com.walterjwhite.csv.api.service.writer.CSVWriterProducer;
import com.walterjwhite.data.pipe.impl.AbstractSink;
import com.walterjwhite.data.pipe.modules.csv.model.CSVSinkConfiguration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import jakarta.inject.Inject;

public class CSVSink extends AbstractSink<String[], CSVSinkConfiguration> {
  protected final CSVWriterProducer csvWriterProducer;
  protected CSVWriter csvWriter;

  @Inject
  public CSVSink(CSVWriterProducer csvWriterProducer) {

    this.csvWriterProducer = csvWriterProducer;
  }

  protected void doConfigure() {
    try {
      csvWriter =
          csvWriterProducer.get(
              new FileWriter(new File(sinkConfiguration.getCsvConfiguration().getFilename())),
              sinkConfiguration.getCsvConfiguration().getHeaders());
      // headers should be initialized
      // csvWriter.writeHeaders(null);
    } catch (IOException e) {
      throw new RuntimeException("Error configuring", e);
    }
  }

  @Override
  public void accept(String[] record) {
    try {
      csvWriter.writeRecord(record);
    } catch (IOException e) {
      throw new RuntimeException("Error writing record to CSV", e);
    }
  }

  @Override
  public void close() throws Exception {
    csvWriter.close();
  }
}
