package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

/** Should a "real" operation be performed or skipped? */
public interface NoOperation extends ConfigurableProperty {
  @DefaultValue boolean DEFAULT = false;
}
