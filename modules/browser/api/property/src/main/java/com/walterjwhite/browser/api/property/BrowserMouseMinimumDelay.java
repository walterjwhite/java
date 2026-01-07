package com.walterjwhite.browser.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface BrowserMouseMinimumDelay extends ConfigurableProperty {
  @DefaultValue int Default = 0;
}
