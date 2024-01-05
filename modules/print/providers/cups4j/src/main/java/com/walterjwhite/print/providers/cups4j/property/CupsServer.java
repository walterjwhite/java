package com.walterjwhite.print.providers.cups4j.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface CupsServer extends ConfigurableProperty {
  @DefaultValue String Default = "localhost";
}
