package com.walterjwhite.property.api.exception;

public class PropertyNotFoundException extends PropertyLoadException {
  public PropertyNotFoundException() {}

  public PropertyNotFoundException(String s) {
    super(s);
  }

  public PropertyNotFoundException(String s, Throwable throwable) {
    super(s, throwable);
  }

  public PropertyNotFoundException(Throwable throwable) {
    super(throwable);
  }

  public PropertyNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
    super(s, throwable, b, b1);
  }
}
