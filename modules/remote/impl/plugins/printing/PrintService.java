package com.walterjwhite.ssh.api.service;

import java.io.File;

public interface PrintService {
  /*PrinterJob*/ void print(final File file /*,Printer printer*/);

  /*PrinterJob*/ void print(final String uri /*,Printer printer*/);
}
