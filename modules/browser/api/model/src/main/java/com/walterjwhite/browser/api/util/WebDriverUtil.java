package com.walterjwhite.browser.api.util;

import com.walterjwhite.browser.api.authentication.field.Locator;
import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.delay.Delayable;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface WebDriverUtil {
  static boolean isPresent(final WebSession webSession, final Locator locator) {
    try {
      webSession.getWebDriver().findElement(locator.getLocatorType().get(locator.getArgument()));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  static void wait(final Delayable delayable) throws InterruptedException {
    if (delayable == null) {
      return;
    }

    if (delayable.isEnabled()) {
      delayable.delay();
    }
  }

  static void wait(final Duration timeout) throws InterruptedException {
    wait(250, timeout.toMillis());
  }

  static void wait(final long minimumDelay, final long maximumDelay) throws InterruptedException {
    final long delay = getDelay(minimumDelay, maximumDelay);
    if (delay > 0) {
      Thread.sleep(delay);
    }
  }

  private static long getDelay(final long minimumDelay, final long maximumDelay) {
    if (minimumDelay == 0 && maximumDelay == 0) {
      return 0;
    }

    return minimumDelay + Math.round(Math.random() * maximumDelay);
  }

  static WebElement waitUntilFound(
      final WebDriver webDriver, final By by, final Duration duration) {
    final WebDriverWait wait = new WebDriverWait(webDriver, duration);
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    // webDriver.getPageSource();
    //    webDriver.s

    return webDriver.findElement(by);
  }

  static void logException(final WebSession webSession, final Throwable t) {
    final Logger logger = LoggerFactory.getLogger(t.getStackTrace()[0].getClassName());

    logger.error(
        String.format(
            "website: %s, startTime: %s, currentPageUrl: ",
            webSession.getWebsiteIdentifier(),
            webSession.getStartDateTime(),
            webSession.getWebDriver().getCurrentUrl()),
        t);
    logger.error(webSession.getWebDriver().getPageSource());
  }

  //  static void logDetailedContextualException(final Stack stack) {
  //    // if current variables include web driver, then use it
  //    final WebDriver webDriver = null;
  //    LOGGER.warn("page source:\n" + webDriver.getPageSource());
  //  }
}
