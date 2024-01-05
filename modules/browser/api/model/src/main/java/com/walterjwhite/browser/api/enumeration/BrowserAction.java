package com.walterjwhite.browser.api.enumeration;

import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import org.openqa.selenium.WebDriver;

public interface BrowserAction {
  void execute(
      final WebDriver webDriver,
      final BrowserUserConfiguration browserUserConfiguration,
      final String... arguments)
      throws InterruptedException;
}
