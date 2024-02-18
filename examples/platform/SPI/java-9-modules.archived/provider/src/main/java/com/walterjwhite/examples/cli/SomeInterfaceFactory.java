package com.walterjwhite.examples.cli;

import com.walterjwhite.examples.lower.LowerSomeInterface;
import com.walterjwhite.examples.upper.UpperSomeInterface;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SomeInterfaceFactory {
  public static SomeInterface getSomeInterface(final String name) {
    if ("lower".equalsIgnoreCase(name)) {
      return new LowerSomeInterface();
    }

    return new UpperSomeInterface();
  }
}
