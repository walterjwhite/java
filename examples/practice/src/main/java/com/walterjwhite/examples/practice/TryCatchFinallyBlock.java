package com.walterjwhite.examples.practice;

public class TryCatchFinallyBlock {
  public static String throwsException(final String input) throws Exception {
    if ("ERROR".equals(input)) {
      throw new Exception("I don't work today.");
    }

    return "response:" + input;
  }

  public static void tryCatchHandler() {}

  public static void main(final String[] args) throws Exception {
    System.out.println(throwsException("test"));
    System.out.println(throwsException("ERROR"));
  }
}
