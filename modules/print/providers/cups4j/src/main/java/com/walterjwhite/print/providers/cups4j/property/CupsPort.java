package com.walterjwhite.print.providers.cups4j.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface CupsPort extends ConfigurableProperty {
  @DefaultValue int Default = 631;
}
