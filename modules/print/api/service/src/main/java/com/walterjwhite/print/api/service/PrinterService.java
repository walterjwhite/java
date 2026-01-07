package com.walterjwhite.print.api.service;

import com.walterjwhite.print.model.PrintJob;
import com.walterjwhite.print.model.PrintRequest;

public interface PrinterService {

  PrintJob print(PrintRequest printRequest) throws Exception;
}
