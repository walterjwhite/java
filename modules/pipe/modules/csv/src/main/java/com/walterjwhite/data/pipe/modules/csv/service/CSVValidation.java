package com.walterjwhite.data.pipe.modules.csv.service;

import java.util.Objects;

public class CSVValidation {
  protected final String filenameKey;
  protected final long count;

  public CSVValidation(String filenameKey, long count) {

    this.filenameKey = filenameKey;
    this.count = count;
  }

  public String getFilenameKey() {
    return filenameKey;
  }

  public long getCount() {
    return count;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CSVValidation that = (CSVValidation) o;
    return Objects.equals(filenameKey, that.filenameKey);
  }

  @Override
  public int hashCode() {

    return Objects.hash(filenameKey);
  }
}
