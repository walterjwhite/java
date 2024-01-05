package com.walterjwhite.property.api.property;

import com.walterjwhite.property.api.annotation.DefaultValue;

// not currently used
@Deprecated
public interface ServiceStopTimeout extends ModuleProperty {
  @DefaultValue int Default = 30;
}
