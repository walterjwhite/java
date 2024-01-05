package com.walterjwhite.csv.impl.legacy.filter;

import com.walterjwhite.csv.api.legacy.CSVRecordFilter;

/** TODO: can this be refactored as a lambda expression? */
public abstract class AbstractCSVRecordFilter implements CSVRecordFilter {
  @Override
  public String[] filter(String[] in) {
    final String[] fields = new String[in.length];
    for (int i = 0; i < in.length; i++) {
      fields[i] = doFilter(in[i]);
    }

    return (fields);
  }

  protected abstract String doFilter(final String in);
}
