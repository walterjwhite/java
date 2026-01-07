package com.walterjwhite.metrics.enumeration;

public enum CounterTag {
  Enter(MeterAttribute.State),
  Catch(MeterAttribute.State),
  Exit(MeterAttribute.State);

  private final MeterAttribute meterAttribute;

  CounterTag(MeterAttribute meterAttribute) {
    this.meterAttribute = meterAttribute;
  }

  public MeterAttribute getMeterAttribute() {
    return meterAttribute;
  }
}
