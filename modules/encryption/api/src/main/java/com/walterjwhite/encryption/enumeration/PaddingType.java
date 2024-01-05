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

  //  @Override
  //  public String getCliArgumentName() {
  //    return getClass().getSimpleName();
  //  }

  //  @Override
  //  public String getDefaultValue() {
  //    // null because this depends on the encryption algorithm
  //    return null;
  //  }
  //
  //  @Override
  //  public String getDescription() {
  //    return "padding to use with the encryption algorithm";
  //  }
}
