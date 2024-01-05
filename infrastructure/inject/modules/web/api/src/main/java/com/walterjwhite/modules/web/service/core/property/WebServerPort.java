package com.walterjwhite.modules.web.service.core.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface WebServerPort extends ConfigurableProperty {
  // place
  @DefaultValue int Default = 8080;
}
