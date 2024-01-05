package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.authentication.field.Locator;
import com.walterjwhite.delay.Delayable;
import com.walterjwhite.delay.annotation.Delayed;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface LocatorUtil {
  @Delayed
  static WebElement locate(
      final Delayable delayable, final WebDriver webDriver, final Locator locator) {
    return webDriver.findElement(locator.getLocatorType().get(locator.getArgument()));
  }
}
