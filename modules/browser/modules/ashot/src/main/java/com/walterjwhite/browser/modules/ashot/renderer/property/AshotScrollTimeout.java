package com.walterjwhite.browser.modules.ashot.renderer.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface AshotScrollTimeout extends ConfigurableProperty {
  @DefaultValue int Default = 100;
}
