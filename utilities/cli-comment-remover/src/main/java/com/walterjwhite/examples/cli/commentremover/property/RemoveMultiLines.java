package com.walterjwhite.examples.cli.commentremover.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface RemoveMultiLines extends ConfigurableProperty {
  @DefaultValue boolean Default = true; 
}
