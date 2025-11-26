package com.walterjwhite.browser.api;

public class NavigationException extends RuntimeException {
  public NavigationException(String reason, Throwable cause) {
    super(reason, cause);
  }
}
