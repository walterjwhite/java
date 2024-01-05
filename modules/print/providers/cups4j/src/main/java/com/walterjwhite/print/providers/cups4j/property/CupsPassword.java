package com.walterjwhite.print.providers.cups4j.property;

import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@Sensitive
public interface CupsPassword extends ConfigurableProperty {
  @DefaultValue String Default = null;
}
