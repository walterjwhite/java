package com.walterjwhite.data.pipe.api.filter;

import java.util.function.Predicate;

public interface Filter<RecordType, FilterConfigurationType extends AbstractFilterConfiguration>
    extends Predicate<RecordType> {
  void configure(FilterConfigurationType filterConfiguration);
}
