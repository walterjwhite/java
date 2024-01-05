package com.walterjwhite.data.pipe.modules.cli;

import com.walterjwhite.data.pipe.api.filter.AbstractFilterConfiguration;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class SimpleFilterConfiguration extends AbstractFilterConfiguration {
  protected String valueToIgnore;

  public SimpleFilterConfiguration(String valueToIgnore) {
    super(SimpleTestFilter.class);
    this.valueToIgnore = valueToIgnore;
  }
}
