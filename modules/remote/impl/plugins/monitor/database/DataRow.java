package com.walterjwhite.remote.impl.service.database;

public class DataRow {

  protected final Object[] columns;
  protected final long lastUpdated;

  public DataRow(final Object[] columns, final long lastUpdated) {

    this.columns = columns;
    this.lastUpdated = lastUpdated;
  }

  public Object[] getColumns() {
    return columns;
  }

  public long getLastUpdated() {
    return lastUpdated;
  }
}
