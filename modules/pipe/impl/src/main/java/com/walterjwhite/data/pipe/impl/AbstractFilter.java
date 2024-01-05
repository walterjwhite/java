package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.filter.AbstractFilterConfiguration;
import com.walterjwhite.data.pipe.api.filter.Filter;

public abstract class AbstractFilter<
        RecordType, FilterConfigurationType extends AbstractFilterConfiguration>
    implements Filter<RecordType, FilterConfigurationType> {
  protected FilterConfigurationType filterConfiguration;

  @Override
  public void configure(FilterConfigurationType filterConfiguration) {
    this.filterConfiguration = filterConfiguration;

    doConfigure();
  }

  protected abstract void doConfigure();
}
