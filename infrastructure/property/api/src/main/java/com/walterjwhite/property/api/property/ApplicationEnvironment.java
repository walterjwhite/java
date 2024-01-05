package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;

public enum ApplicationEnvironment implements ConfigurableProperty, ApplicationManifestProperty {
  @DefaultValue
  Development,
  Testing,
  Production
}
