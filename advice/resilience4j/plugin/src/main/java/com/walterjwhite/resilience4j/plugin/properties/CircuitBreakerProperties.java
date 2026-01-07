package com.walterjwhite.resilience4j.plugin.properties;

import lombok.Data;

@Data
public class CircuitBreakerProperties extends AbstractProperties {
  protected int durationInOpenState;
  protected int failureRateThreshold;
  protected int ringBufferSizeInClosedState;
  protected int ringBufferSizeInHalfOpenState;
}
