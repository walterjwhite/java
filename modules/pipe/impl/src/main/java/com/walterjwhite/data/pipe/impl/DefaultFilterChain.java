package com.walterjwhite.data.pipe.impl;

import com.walterjwhite.data.pipe.api.filter.AbstractFilterConfiguration;
import com.walterjwhite.data.pipe.api.filter.Filter;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

public class DefaultFilterChain implements Predicate {
  protected final Set<Filter> filters = new HashSet<>();

  public DefaultFilterChain(final Set<AbstractFilterConfiguration> filterConfigurations) {

    for (final AbstractFilterConfiguration filterConfiguration : filterConfigurations) {
      Filter filter =
          ApplicationHelper.getApplicationInstance()
              .getInjector()
              .getInstance(filterConfiguration.getFilterClass());
      filter.configure(filterConfiguration);
      filters.add(filter);
    }
  }

  @Override
  public boolean test(Object o) {
    for (final Filter filter : filters) if (!filter.test(o)) return false;

    return true;
  }
}
