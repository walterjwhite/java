package com.walterjwhite.transform.key_value;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ValueType {
  Int(java.lang.Integer::valueOf),
  Float(java.lang.Float::valueOf),
  Short(java.lang.Short::valueOf),
  Long(java.lang.Long::valueOf),
  Byte(java.lang.Byte::valueOf),
  Double(java.lang.Double::valueOf),
  Boolean(java.lang.Boolean::valueOf);

  private final Function<String, Object> conversionFunction;

  public static Optional<ValueType> get(final String typeName) {
    return Arrays.stream(values())
        .filter(type -> type.name().equalsIgnoreCase(typeName))
        .findFirst();
  }
}
