package com.walterjwhite.browser.api;

import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebElementAction {
  void execute(
      final WebDriver webDriver,
      final BrowserUserConfiguration browserUserConfiguration,
      final WebElement webElement,
      final Object... arguments)
      throws InterruptedException;
}
