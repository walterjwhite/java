package com.walterjwhite.examples.ssh;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface ExampleSSHUser extends ConfigurableProperty {
  @DefaultValue String DEFAULT_USERNAME = "test";
}
