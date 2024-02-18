package com.walterjwhite.ssh.api;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;


public interface SSHPublicKeyPath extends ConfigurableProperty {
  @DefaultValue String DEFAULT = "";
}
