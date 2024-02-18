package com.walterjwhite.metrics.plugin;

import com.walterjwhite.metrics.plugin.enumeration.MeterAttribute;

public class TagParameter {
  protected final MeterAttribute meterAttribute;
  protected final Object argument;

  public TagParameter(MeterAttribute meterAttribute, Object argument) {
    this.meterAttribute = meterAttribute;
    this.argument = argument;
  }

  public MeterAttribute getMeterAttribute() {
    return meterAttribute;
  }

  public Object getArgument() {
    return argument;
  }
}
