package com.walterjwhite.index.modules.elasticsearch.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

// any number of schema, host, and port specifications, comma-separated
public interface ElasticSearchNodes extends ConfigurableProperty {
  @DefaultValue String Default = "http://localhost:9200";
}
