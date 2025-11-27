package com.walterjwhite.metrics;

import com.walterjwhite.metrics.enumeration.MeterAttribute;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagParameter {
  protected final MeterAttribute meterAttribute;
  protected final Object argument;
}
