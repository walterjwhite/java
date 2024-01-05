package com.walterjwhite.logging.formatter.subclass;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TestCaseResult {
  protected final TestCase testCase;
  // protected Execution execution;
  protected final Object output;
  protected final Exception exception;
}
