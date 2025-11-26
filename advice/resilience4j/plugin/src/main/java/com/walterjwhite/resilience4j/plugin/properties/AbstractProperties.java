package com.walterjwhite.resilience4j.plugin.properties;

import lombok.Data;

@Data
public abstract class AbstractProperties {
  protected int maxConcurrentCalls;
  protected int maxWaitTime;
}
