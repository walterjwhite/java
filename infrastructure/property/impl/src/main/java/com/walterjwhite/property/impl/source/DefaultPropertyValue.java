package com.walterjwhite.property.impl.source;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@RequiredArgsConstructor
public class DefaultPropertyValue implements PropertyValue {
  // protected final Class<? extends ConfigurableProperty> configurablePropertyClass;

  /**
   * Whether this value is expected to be a String, Integer, Double, Float, Long, Short, Number,
   * Byte, Char, Enum, Date/Time, Boolean, BigInteger, BigDecimal
   */
  protected final Class propertyType;

  // NOTE: encrypted values are always stored as a string until they're decrypted
  protected final String value;
}
