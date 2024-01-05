package com.walterjwhite.print.providers.java;

import com.google.inject.AbstractModule;
import com.walterjwhite.print.api.service.PrinterService;

public class NativePrintModule extends AbstractModule {
  @Override
  protected void configure() {
    bind(PrinterService.class).to(NativePrinterService.class);
  }
}
