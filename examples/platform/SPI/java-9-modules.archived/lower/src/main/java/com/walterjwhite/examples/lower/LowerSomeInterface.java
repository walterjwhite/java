package com.walterjwhite.examples.lower;

import com.walterjwhite.examples.SPI.SomeInterface;

public class LowerSomeInterface implements SomeInterface {
  @Override
  public String process(final String input) {
    if (input == null) return input;

    return input.toLowerCase();
  }
}
