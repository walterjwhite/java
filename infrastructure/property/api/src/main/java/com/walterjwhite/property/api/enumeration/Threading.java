package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

/** Number of threads system should use * */
// this is not used, the # of threads used by other services has a specific property
// consider removing this
@Deprecated
public interface Threading extends ConfigurableProperty {
  @DefaultValue int DEFAULT = Runtime.getRuntime().availableProcessors() * 2;
}
