package com.walterjwhite.print.providers.java;

public class NoPrinterSelectedException extends RuntimeException {
  public NoPrinterSelectedException(String s) {
    super(s);
  }
}
