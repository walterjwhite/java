package com.walterjwhite.csv.impl.legacy.filter;

/** Configure several instances of these: \r \n " ' */
public class CharacterReplacingCSVRecordFilter extends AbstractCSVRecordFilter {
  protected final String replace;
  protected final String with;

  public CharacterReplacingCSVRecordFilter(String replace, String with) {
    this.replace = replace;
    this.with = with;
  }

  public CharacterReplacingCSVRecordFilter(String replace) {
    this.replace = replace;
    this.with = "";
  }

  @Override
  protected String doFilter(String in) {
    return in.replaceAll(replace, with);
  }
}
