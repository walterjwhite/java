package com.walterjwhite.data.pipe.modules.csv.service;

import com.walterjwhite.csv.api.service.reader.CSVReader;
import com.walterjwhite.csv.api.service.reader.CSVReaderProducer;
import com.walterjwhite.csv.impl.service.CSVRecordCounter;
import java.io.*;
import java.util.function.Function;

public class CSVValidationMap implements Function<CSVValidation, Long> {
  protected final CSVReaderProducer csvReaderProducer;
  protected final CSVRecordCounter csvRecordCounter;

  public CSVValidationMap(CSVReaderProducer csvReaderProducer, CSVRecordCounter csvRecordCounter) {

    this.csvReaderProducer = csvReaderProducer;
    this.csvRecordCounter = csvRecordCounter;
  }

  @Override
  public Long apply(CSVValidation csvValidation) {
    try {
      return Long.valueOf(countTargetFile(new File(csvValidation.getFilenameKey())));

    } catch (IOException e) {
      throw new RuntimeException("Error validating counts", e);
    }
  }


  protected File getTargetFile(final File csvCountFile, final String name) {
    return (new File(
        csvCountFile.getParentFile().getAbsolutePath() + File.separator + name + ".csv"));
  }

  protected int countTargetFile(final File targetCSVFile) throws IOException {
    final CSVReader csvReader =
        csvReaderProducer.get(
            new InputStreamReader(new BufferedInputStream(new FileInputStream(targetCSVFile))));

    return (csvRecordCounter.count(csvReader));
  }
}
