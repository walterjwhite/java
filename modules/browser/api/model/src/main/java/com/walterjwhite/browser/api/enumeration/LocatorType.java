package com.walterjwhite.browser.api.enumeration;

import org.openqa.selenium.By;

public enum LocatorType {
  XPath {
    public By get(final String argument) {
      return By.xpath(argument);
    }
  },
  Id {
    public By get(final String argument) {
      return By.id(argument);
    }
  },
  ;

  public abstract By get(final String argument);
}
