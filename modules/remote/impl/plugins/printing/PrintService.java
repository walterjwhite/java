package com.walterjwhite.ssh.api.service;

import java.io.File;

/**
 * TODO: support printing. IE. I am at home, but want to print something so that when I get to the
 * office, it will be there
 */
public interface PrintService {
  /*PrinterJob*/ void print(final File file /*,Printer printer*/);

  /*PrinterJob*/ void print(final String uri /*,Printer printer*/);
}
