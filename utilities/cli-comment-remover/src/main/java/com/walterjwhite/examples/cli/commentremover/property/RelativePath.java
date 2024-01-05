package com.walterjwhite.examples.cli.commentremover.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@PropertyValueType(String.class)
public interface RelativePath extends ConfigurableProperty {
  @DefaultValue String Default = ""; // start in current directory
}
