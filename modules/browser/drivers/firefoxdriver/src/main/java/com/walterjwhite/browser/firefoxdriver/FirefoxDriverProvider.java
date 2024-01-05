package com.walterjwhite.browser.firefoxdriver;

import com.walterjwhite.browser.api.model.BrowserConfiguration;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverProvider implements Provider<WebDriver> {
  protected final BrowserConfiguration browserConfiguration;

  @Inject
  public FirefoxDriverProvider(final BrowserConfiguration browserConfiguration) {

    this.browserConfiguration = browserConfiguration;
  }

  @Override
  public WebDriver get() {
    if (browserConfiguration.getProxyHost() != null) {
      Proxy proxy = new Proxy();

      if (browserConfiguration.getProxyType().get().equalsIgnoreCase("http")) {
        proxy.setHttpProxy(
            browserConfiguration.getProxyHost() + ":" + browserConfiguration.getProxyPort());
      } else if (browserConfiguration.getProxyType().get().equalsIgnoreCase("socks")) {
        proxy.setSocksProxy(
            browserConfiguration.getProxyHost() + ":" + browserConfiguration.getProxyPort());
      } else {
        throw new IllegalArgumentException(
            browserConfiguration.getProxyType().get() + " is not supported");
      }

      final FirefoxOptions firefoxOptions = new FirefoxOptions();
      firefoxOptions.setCapability("proxy", proxy);

      // chromeDriver.getCapabilities().getCapability(CapabilityType.PROXY)
      return new AutoCloseableFirefoxDriver(firefoxOptions);
    }

    return new AutoCloseableFirefoxDriver();
  }
}
