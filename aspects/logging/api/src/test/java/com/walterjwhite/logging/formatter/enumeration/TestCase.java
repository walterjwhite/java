package com.walterjwhite.logging.formatter.enumeration;

public interface TestCase {
  void validate(final Object result);

  default void validateException(final Exception exception) {
    throw new AssertionError("Unexpected exception:", exception);
  }
}
