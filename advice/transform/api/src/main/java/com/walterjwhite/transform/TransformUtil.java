package com.walterjwhite.transform;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransformUtil {
  public static Class getClass(final String name) {
    try {
      return Class.forName(name);
    } catch (ClassNotFoundException e) {
      throw new Error("Unable to locate class", e);
    }
  }
}
