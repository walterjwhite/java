package com.walterjwhite.metrics.modules.elastic;

import io.micrometer.elastic.ElasticConfig;

/** TODO: leverage the index/elasticsearch module for configuration */
public class DefaultElasticConfig implements ElasticConfig {
  //  @Override
  //  public String uri() {
  //    return "http://localhost:7101/api/v1/publish";
  //  }

  @Override
  public String get(String k) {
    return null;
  }
}
