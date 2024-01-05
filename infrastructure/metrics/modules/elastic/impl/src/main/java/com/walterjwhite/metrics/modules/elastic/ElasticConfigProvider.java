package com.walterjwhite.metrics.modules.elastic;

import io.micrometer.elastic.ElasticConfig;
import jakarta.inject.Provider;

public class ElasticConfigProvider implements Provider<ElasticConfig> {
  protected final ElasticConfig elasticConfig = new DefaultElasticConfig();

  @Override
  public ElasticConfig get() {
    return elasticConfig;
  }
}
