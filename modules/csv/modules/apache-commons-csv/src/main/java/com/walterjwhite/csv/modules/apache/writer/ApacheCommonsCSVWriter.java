package com.walterjwhite.csv.modules.apache.writer;

import com.walterjwhite.csv.api.service.writer.CSVWriter;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class ApacheCommonsCSVWriter implements CSVWriter {
  protected final CSVFormat csvFormat;
  protected final CSVPrinter csvPrinter;

  public ApacheCommonsCSVWriter(final CSVFormat csvFormat, final Appendable outputAppendable)
      throws IOException {

    this.csvFormat = csvFormat;
    csvPrinter = csvFormat.print(outputAppendable);
  }

  @Override
  public void writeRecord(String[] record) throws IOException {
    csvPrinter.printRecord(record);
  }

  @Override
  public void close() throws Exception {
    csvPrinter.flush();
    csvPrinter.close();
  }
}
