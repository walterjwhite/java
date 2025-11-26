package com.walterjwhite.resilience4j.plugin.properties;

import lombok.Data;

@Data
public class RateLimitProperties extends AbstractProperties {
  protected int limitForPeriod;
  protected int limitRefreshPeriod;
  protected int timeoutDuration;
}
