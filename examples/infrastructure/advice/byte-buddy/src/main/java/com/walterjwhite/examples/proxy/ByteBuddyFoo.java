package com.walterjwhite.examples.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ByteBuddyFoo {
  @Override
  public String toString() {
    return "ByteBuddyFoo{}";
  }

  public void anotherMethod(final String input) {
    LOGGER.info("another method: {}", input);
  }
}
