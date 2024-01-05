package com.walterjwhite.web.jetty.servlet;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface ServletContextPath extends ConfigurableProperty {
  @DefaultValue String Default = "/servlets";
}
