package com.walterjwhite.remote.impl.plugins.printing;

// import com.walterjwhite.queue.api.queuedJob.AbstractCallableJob;

import jakarta.inject.Inject;

public class PrintRequestMessageCallable
/*extends AbstractCallableJob<PrintRequestMessage, Void>*/ {
  //  protected final PrinterService printerService;

  @Inject
  public PrintRequestMessageCallable(/*PrinterService printerService*/ ) {

    //    this.printerService = printerService;
  }

  //  @Override
  //  protected boolean isRetryable(Throwable thrown) {
  //    return false;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  // entity.getPrintRequest()
  //    // and put that into the queue?
  //    //    printerService.print(entity.getPrintRequest());
  //    return null;
  //  }
}
