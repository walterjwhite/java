package com.walterjwhite.property.impl.source;

public interface PropertyValue {
  Class getPropertyType();

  // NOTE: encrypted values are always stored as a string until they're decrypted
  String getValue();
}
