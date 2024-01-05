package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.impl.AbstractFilter;

public class SimpleTestFilter extends AbstractFilter<String[], SimpleFilterConfiguration> {
  @Override
  protected void doConfigure() {}

  @Override
  public boolean test(String[] strings) {
    for (final String string : strings)
      if (filterConfiguration.getValueToIgnore().equals(string)) return false;

    return true;
  }
}
