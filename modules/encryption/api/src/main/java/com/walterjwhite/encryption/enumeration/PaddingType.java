package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

public enum PaddingType implements ConfigurableProperty /*, CommandLineOption*/ {
  NoPadding,
  @DefaultValue
  PKCS5Padding,
  PKCS1Padding,
  OAEPWithSHA_1AndMGF1Padding,
  OAEPWithSHA_256AndMGF1Padding;

  public String getName() {
    return (toString().replace("_", "-"));
  }


}
