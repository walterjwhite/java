package com.walterjwhite.browser.plugins.virgin.pulse.api;

import com.walterjwhite.browser.api.enumeration.LocatorType;
import com.walterjwhite.browser.api.enumeration.WebElementBrowserAction;
import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.browser.api.util.WebDriverUtil;

public enum ElementAction {
  Click {
    @Override
    public void execute(
        final WebSession webSession,
        final LocatorType locatorType,
        final String argument,
        final boolean initial,
        String... arguments)
        throws InterruptedException {
      doInitial(webSession, locatorType, argument, initial);

      if (!initial) {
        WebElementBrowserAction.Click.execute(
            webSession.getWebDriver(), webSession, locatorType, argument, arguments);
      }
    }
  },
  Type {
    @Override
    public void execute(
        final WebSession webSession,
        final LocatorType locatorType,
        final String argument,
        final boolean initial,
        String... arguments)
        throws InterruptedException {
      doInitial(webSession, locatorType, argument, initial);

      WebElementBrowserAction.Type.execute(
          webSession.getWebDriver(), webSession, locatorType, argument, arguments);
    }
  };

  protected void doInitial(
      final WebSession webSession,
      final LocatorType locatorType,
      final String argument,
      final boolean initial)
      throws InterruptedException {
    if (!initial) {
      return;
    }

    WebDriverUtil.waitUntilFound(
        webSession.getWebDriver(),
        locatorType.get(argument),
        webSession.getElementLocatorWaitDuration());

    WebElementBrowserAction.MoveTo.execute(
        webSession.getWebDriver(), webSession, locatorType, argument);

    WebElementBrowserAction.Click.execute(
        webSession.getWebDriver(), webSession, locatorType, argument);
  }

  public void execute(
      final WebSession webSession,
      final LocatorType locatorType,
      final String argument,
      final String... arguments)
      throws InterruptedException {
    execute(webSession, locatorType, argument, true, arguments);
  }

  public abstract void execute(
      final WebSession webSession,
      final LocatorType locatorType,
      final String argument,
      final boolean initial,
      final String... arguments)
      throws InterruptedException;
}
