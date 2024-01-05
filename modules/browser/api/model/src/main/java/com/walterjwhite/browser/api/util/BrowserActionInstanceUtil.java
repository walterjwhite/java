package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.model.BrowserActionInstance;
import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import org.openqa.selenium.WebDriver;

public interface BrowserActionInstanceUtil {
  static void execute(
      final BrowserUserConfiguration browserUserConfiguration,
      final WebDriver webDriver,
      final BrowserActionInstance[] browserActionInstances)
      throws InterruptedException {
    if (browserActionInstances == null) {
      return;
    }

    for (final BrowserActionInstance browserActionInstance : browserActionInstances) {
      browserActionInstance
          .getBrowserAction()
          .execute(
              webDriver,
              browserUserConfiguration,
              browserActionInstance.getLocatorType(),
              browserActionInstance.getLocatorArgument(),
              browserActionInstance.getArguments());
    }
  }
}
