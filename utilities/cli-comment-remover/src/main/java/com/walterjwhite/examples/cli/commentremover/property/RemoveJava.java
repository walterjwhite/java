package com.walterjwhite.examples.cli.commentremover.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@PropertyValueType(int.class)
public interface RemoveJava extends ConfigurableProperty {
  @DefaultValue boolean Default = true; // remove Java comments
}