package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.property.api.annotation.DefaultValue;
import com.walterjwhite.property.api.property.ConfigurableProperty;

// http://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html#Cipher
public enum TransformationAlgorithm implements ConfigurableProperty {
  @DefaultValue
  CBC

  //  @Override
  //  public String getDefaultValue() {
  //    return CBC.getName();
  //  }
  //
  //  @Override
  //  public String getDescription() {
  //    return "Transformation algorithm";
  //  }
  //
  //  @Override
  //  public String getName() {
  //    return getClass().getName();
  //  }
}
