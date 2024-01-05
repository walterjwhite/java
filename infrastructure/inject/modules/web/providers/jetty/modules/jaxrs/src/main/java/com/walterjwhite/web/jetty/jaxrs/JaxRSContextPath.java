package com.walterjwhite.web.jetty.jaxrs;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface JaxRSContextPath extends ConfigurableProperty {
  @DefaultValue String Default = "/jaxrs";
}
