package com.walterjwhite.csv.impl.legacy.filter;

public class CarriageReturnRemovingCSVRecordFilter extends AbstractCSVRecordFilter {

  @Override
  protected String doFilter(String in) {
    return in.replaceAll("\r", "");
  }
}
