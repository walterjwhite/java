package com.walterjwhite.browser.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface BrowserMouseMinimumDelay extends ConfigurableProperty {
  // in milliseconds (0 seconds)
  @DefaultValue int Default = 0;
}
