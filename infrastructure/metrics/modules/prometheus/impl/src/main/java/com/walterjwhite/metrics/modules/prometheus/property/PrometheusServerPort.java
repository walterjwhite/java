package com.walterjwhite.metrics.modules.prometheus.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@PropertyValueType(int.class)
public interface PrometheusServerPort extends ConfigurableProperty {
  @DefaultValue int Default = 8181;

  //  default Class getType() {
  //    return int.class;
  //  }
}
