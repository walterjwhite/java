package com.walterjwhite.data.pipe.api.filter;

public abstract class AbstractFilterConfiguration {
  protected final Class<? extends Filter> filterClass;

  public AbstractFilterConfiguration(Class<? extends Filter> filterClass) {
    this.filterClass = filterClass;
  }

  public Class<? extends Filter> getFilterClass() {
    return filterClass;
  }
}
