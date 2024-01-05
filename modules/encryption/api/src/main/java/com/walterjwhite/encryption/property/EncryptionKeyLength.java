package com.walterjwhite.encryption.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface EncryptionKeyLength extends ConfigurableProperty {
  @DefaultValue int L_256 = 32;
  int L_512 = 64;
}
