package com.walterjwhite.print.providers.cups4j;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.NoOperation;
import jakarta.inject.Inject;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;

public class Cups4JPrinterService /*extends AbstractPrinterService*/ {
  protected final CupsClient cupsClient;

  @Inject
  public Cups4JPrinterService(
      FileStorageService fileStorageService,
      CupsClient cupsClient,
      @Property(NoOperation.class) boolean isNooperation) {
    this.cupsClient = cupsClient;
  }

  protected void doPrint(PrintJob printJob) throws Exception {
    getCupsPrinter(printJob)
        .print(
            new org.cups4j.PrintJob.Builder(
                    new BufferedInputStream(
                        new FileInputStream(printJob.getPrintRequest().getFile().getSource())))
                .build());
  }

  protected CupsPrinter getCupsPrinter(PrintJob printJob) throws Exception {
    return cupsClient.getPrinter(new URL(printJob.getPrinter().getUri()));
  }
}
