package com.walterjwhite.index.modules.elasticsearch.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface ElasticSearchResultSize extends ConfigurableProperty {
  @DefaultValue int Default = 1000; // records
}
