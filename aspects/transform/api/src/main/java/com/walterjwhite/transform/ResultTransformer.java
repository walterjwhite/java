package com.walterjwhite.transform;

import java.util.function.Function;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResultTransformer {
  public static Object transform(final Object value, final Function<Object, Object> function) {
    try {
      return TransformedType.get(value).transform(value, function);
    } catch (IllegalArgumentException e) {
      return value;
    }
  }
}
