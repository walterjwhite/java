package com.walterjwhite.print.api.service;

import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;

public interface PrinterService {
  //  void print(final Printer printer, final Pageable pageable)
  //      throws PrintException, PrinterException;
  //

  PrintJob print(PrintRequest printRequest) throws Exception;
}
