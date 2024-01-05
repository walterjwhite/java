package com.walterjwhite.metrics.modules.prometheus;

import io.micrometer.prometheus.PrometheusConfig;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DefaultPrometheusConfig implements PrometheusConfig {
  protected Map<String, String> map = new HashMap<>();

  protected boolean descriptions;
  protected Duration step;
  protected String prefix;

  public DefaultPrometheusConfig(boolean descriptions, Duration step, String prefix) {
    this.descriptions = descriptions;
    this.step = step;
    this.prefix = prefix;
  }

  @Override
  public String get(String key) {
    return map.get(key);
  }

  @Override
  public String prefix() {
    return prefix;
  }

  @Override
  public boolean descriptions() {
    return descriptions;
  }

  @Override
  public Duration step() {
    return step;
  }
}
