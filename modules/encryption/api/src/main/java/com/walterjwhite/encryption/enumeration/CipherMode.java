package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.annotation.PropertyValueType;
import com.walterjwhite.property.api.property.ConfigurableProperty;

@PropertyValueType(CipherMode.class)
public enum CipherMode implements ConfigurableProperty {
  @DefaultValue
  CBC,
  ECB
}
