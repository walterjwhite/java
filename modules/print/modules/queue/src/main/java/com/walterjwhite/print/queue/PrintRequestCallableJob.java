package com.walterjwhite.print.queue;

import com.walterjwhite.print.api.service.PrinterService;
import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;
import com.walterjwhite.queue.api.job.AbstractCallableJob;
import jakarta.inject.Inject;

public class PrintRequestCallableJob extends AbstractCallableJob<PrintRequest, PrintJob> {
  protected final PrinterService printerService;

  @Inject
  public PrintRequestCallableJob(PrinterService printerService) {
    this.printerService = printerService;
  }

  @Override
  protected boolean isRetryable(Throwable thrown) {
    return false;
  }

  @Override
  public PrintJob call() throws Exception {
    return printerService.print(entity);
  }
}
