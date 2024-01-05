package com.walterjwhite.test;

import java.lang.reflect.Method;

public class TestCase {
  protected final Class classUnderTest;
  protected final Method methodUnderTest;
  protected final Object[] arguments;
  // protected final Object expectedResult;
  // protected final Exception expectedException;

  public TestCase(Class classUnderTest, Method methodUnderTest, Object[] arguments) {
    this.classUnderTest = classUnderTest;
    this.methodUnderTest = methodUnderTest;
    this.arguments = arguments;
  }
}
