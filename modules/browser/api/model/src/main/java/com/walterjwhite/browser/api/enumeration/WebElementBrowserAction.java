package com.walterjwhite.browser.api.enumeration;

import com.walterjwhite.browser.api.authentication.field.Locator;
import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import com.walterjwhite.browser.api.util.WebDriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public enum WebElementBrowserAction /*implements BrowserAction*/ {
  //    Locate {
  //      public void execute(
  //              final WebDriver webDriver,
  //              final BrowserUserConfiguration browserUserConfiguration,
  //              final String... arguments)
  //              throws InterruptedException {
  //        final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
  //                webDriver.findElement(locatorType.get(arguments[1]));
  //      }
  //    },

  Click {
    protected void doExecute(
        final WebDriver webDriver,
        final BrowserUserConfiguration browserUserConfiguration,
        final WebElement webElement,
        final Object... arguments)
        throws InterruptedException {
      WebDriverUtil.wait(
          browserUserConfiguration
              .getBrowserActionDelayableMap()
              .get(WebElementBrowserAction.Click));
      webElement.click();
    }
  },
  Sendkeys {
    protected void doExecute(
        final WebDriver webDriver,
        final BrowserUserConfiguration browserUserConfiguration,
        final WebElement webElement,
        final Object... arguments)
        throws InterruptedException {

      WebDriverUtil.wait(
          browserUserConfiguration
              .getBrowserActionDelayableMap()
              .get(WebElementBrowserAction.Sendkeys));

      final boolean clear;
      if (arguments.length == 1) {
        clear = false;
      } else if (arguments.length == 2) {
        clear = (Boolean) arguments[1];
      } else {
        throw new IllegalStateException("Expected either 1 or 2 arguments:" + arguments.length);
      }

      if (clear) {
        webElement.clear();
      }
      webElement.sendKeys((String) arguments[0]);
    }
  },
  Type {
    protected void doExecute(
        final WebDriver webDriver,
        final BrowserUserConfiguration browserUserConfiguration,
        final WebElement webElement,
        final Object... arguments)
        throws InterruptedException {
      final boolean clear;
      if (arguments.length == 1) {
        clear = false;
      } else if (arguments.length == 2) {
        clear = (Boolean) arguments[1];
      } else {
        throw new IllegalStateException("Expected either 1 or 2 arguments:" + arguments.length);
      }

      if (clear) {
        webElement.clear();
      }
      //      webElement.sendKeys(arguments[0]);

      final String textToType = ((String) arguments[0]);
      for (int i = 0; i < textToType.length(); i++) {
        Sendkeys.doExecute(
            webDriver,
            browserUserConfiguration,
            webElement,
            String.valueOf(textToType.charAt(i)),
            false);
      }
    }
  },
  MoveTo {
    protected void doExecute(
        final WebDriver webDriver,
        final BrowserUserConfiguration browserUserConfiguration,
        final WebElement webElement,
        final Object... arguments)
        throws InterruptedException {
      WebDriverUtil.wait(browserUserConfiguration.getElementLocatorWaitDuration());

      final Actions action = new Actions(webDriver);
      action.moveToElement(webElement).build().perform();
    }
  };
  //  WaitUtilVisible {
  //    @Override
  //    public void execute(
  //        final WebDriver webDriver,
  //        final BrowserUserConfiguration browserUserConfiguration,
  //        final String... arguments) {
  //      final LocatorType locatorType = LocatorType.valueOf(arguments[0]);
  //      Duration duration = Duration.ofSeconds(Long.valueOf(arguments[2]));
  //
  //      executeSpecial(
  //          webDriver, browserUserConfiguration, new Locator(locatorType, arguments[1]),
  // duration);
  //    }
  //
  //    public void executeSpecial(
  //        final WebDriver webDriver,
  //        final BrowserUserConfiguration browserUserConfiguration,
  //        final Locator locator,
  //        final Duration duration) {
  //      final WebDriverWait wait = new WebDriverWait(webDriver, duration);
  //      wait.until(
  //          ExpectedConditions.visibilityOfElementLocated(
  //              locator.getLocatorType().get(locator.getArgument())));
  //    }
  //  };
  //  SecretSendkeys {
  //    public void execute(
  //        final WebDriver webDriver,
  //        final BrowserUserConfiguration browserUserConfiguration,
  //        final String... arguments)
  //        throws InterruptedException {
  //      // final String value = secretService.get(arguments[2]);
  //      final String[] secretArguments = new String[arguments.length];
  //      Sendkeys.execute(webDriver, browserUserConfiguration, secretArguments);
  //    }
  //  },
  //  SecretType {
  //    public void execute(
  //        final WebDriver webDriver,
  //        final BrowserUserConfiguration browserUserConfiguration,
  //        final String... arguments)
  //        throws InterruptedException {
  //      // final String value = secretService.get(arguments[2]);
  //      final String[] secretArguments = new String[arguments.length];
  //      Type.execute(webDriver, browserUserConfiguration, secretArguments);
  //    }
  //  };

  public WebElement execute(
      final WebDriver webDriver,
      final BrowserUserConfiguration browserUserConfiguration,
      final LocatorType locatorType,
      final String locatorArgument,
      final Object... arguments)
      throws InterruptedException {
    final WebElement webElement = webDriver.findElement(locatorType.get(locatorArgument));
    doExecute(webDriver, browserUserConfiguration, webElement, arguments);
    return webElement;
  }

  //  private static String[] getArguments(final String[] arguments) {
  //    if (arguments == null || arguments.length < 3) {
  //      return null;
  //    }
  //
  //    return Arrays.copyOfRange(arguments, 2, arguments.length - 1);
  //  }

  public WebElement execute(
      final WebDriver webDriver,
      final BrowserUserConfiguration browserUserConfiguration,
      final Locator locator,
      final Object... arguments)
      throws InterruptedException {
    return execute(
        webDriver,
        browserUserConfiguration,
        locator.getLocatorType(),
        locator.getArgument(),
        arguments);
  }

  protected void doExecute(
      final WebDriver webDriver,
      final BrowserUserConfiguration browserUserConfiguration,
      final WebElement webElement,
      final Object... arguments)
      throws InterruptedException {}
}
