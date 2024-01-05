package com.walterjwhite.examples.transform;

public class NonDIObject {
  public static String testMethod(final String argument1, final String argument2) {
    return "ORIGINAL METHOD: " + argument1 + ":" + argument2;
  }

  public static String testMethodKeyValue(final String argument1, final String argument2) {
    return "ORIGINAL METHOD Key Value: " + argument1 + ":" + argument2;
  }

  public static String hashAnotherMethod(final String argument1, final String argument2) {
    return "ORIGINAL METHOD: " + argument1 + ":" + argument2;
  }
}
