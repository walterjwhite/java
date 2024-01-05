package com.walterjwhite.browser.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface LogJavascript extends ConfigurableProperty {
  @DefaultValue boolean Default = false;
}
