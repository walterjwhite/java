package com.walterjwhite.property.api.exception;

public class PropertyLoadException extends RuntimeException {
  public PropertyLoadException() {}

  public PropertyLoadException(String s) {
    super(s);
  }

  public PropertyLoadException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public PropertyLoadException(Throwable throwable) {
    super(throwable);
  }

  public PropertyLoadException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
