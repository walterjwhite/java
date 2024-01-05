package com.walterjwhite.csv.impl.legacy.filter;

public class NewlineRemovingCSVRecordFilter extends AbstractCSVRecordFilter {

  @Override
  protected String doFilter(String in) {
    return in.replaceAll("\n", "");
  }
}
