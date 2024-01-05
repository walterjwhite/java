package com.walterjwhite.encryption.property;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public interface EncryptionSaltLength extends ConfigurableProperty {
  @DefaultValue int L_8 = 8;
  int L16 = 16;
}
