package com.walterjwhite.browser.firefoxdriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverService;
import org.openqa.selenium.firefox.FirefoxOptions;

// @Data
public class AutoCloseableFirefoxDriver extends FirefoxDriver implements AutoCloseable {
  //  protected WebSession webSession;

  public AutoCloseableFirefoxDriver() {}

  public AutoCloseableFirefoxDriver(FirefoxOptions options) {
    super(options);
  }

  public AutoCloseableFirefoxDriver(FirefoxDriverService service) {
    super(service);
  }

  public AutoCloseableFirefoxDriver(FirefoxDriverService service, FirefoxOptions options) {
    super(service, options);
  }

  @Override
  public void close() {
    //    if(webSession == null || webSession.isLogoutOnClose()) {
    // super.close();
    quit();
    //    }
  }
}
