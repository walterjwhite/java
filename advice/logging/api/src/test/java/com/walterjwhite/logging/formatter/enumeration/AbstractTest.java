package com.walterjwhite.logging.formatter.enumeration;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractTest<TestClass, TestClassTestCase extends TestCase> {
  protected final Class<TestClass> classUnderTest;
  protected final TestClassTestCase[] testCases;
  protected final TestClass testInstance;

  public void run() {
    Arrays.stream(testCases).forEach(testCase -> runTestCase(testCase));
  }

  protected void runTestCase(TestClassTestCase testClassTestCase) {
    try {
      testClassTestCase.validate(doRun(testClassTestCase));
    } catch (Exception e) {
      testClassTestCase.validateException(e);
    }
  }

  protected abstract Object doRun(TestClassTestCase testClassTestCase);
}
