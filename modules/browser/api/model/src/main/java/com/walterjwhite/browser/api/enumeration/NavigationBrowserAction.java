package com.walterjwhite.browser.api.enumeration;

import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import org.openqa.selenium.WebDriver;

public enum NavigationBrowserAction /*implements BrowserAction*/ {
  Get {
    //    @Override
    public void execute(
        WebDriver webDriver,
        BrowserUserConfiguration browserUserConfiguration,
        String... arguments) {
      webDriver.get(arguments[0]);
    }
  }
}
