package com.walterjwhite.logging.formatter.subclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TestCase {
  protected final Class classUnderTest;
  protected final Object input;
}
