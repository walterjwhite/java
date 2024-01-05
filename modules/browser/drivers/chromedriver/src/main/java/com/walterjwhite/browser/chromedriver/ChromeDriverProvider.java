package com.walterjwhite.browser.chromedriver;

import com.walterjwhite.browser.api.model.BrowserConfiguration;
import com.walterjwhite.browser.api.service.SessionAwareWebDriver;
import java.util.Collections;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class ChromeDriverProvider implements Provider<SessionAwareWebDriver> {
  protected final BrowserConfiguration browserConfiguration;

  @Override
  public SessionAwareWebDriver get() {
    final ChromeOptions chromeOptions = new ChromeOptions();
    //    chromeOptions.setBrowserVersion("97.0");
    //    chromeOptions.setCapability("version", "97.0");
    //
    //    chromeOptions.addArguments(
    //        "--user-agent=Mozilla/5.0 (X11; FreeBSD amd64; Linux x86_64) AppleWebKit/550.0 (KHTML,
    // like"
    //            + " Gecko) Chrome/97.0 Safari/550.0",
    //        "--disable-features=UserAgentClientHint");

    LOGGER.warn("Browser Version:" + chromeOptions.getBrowserVersion());
    //    final DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    //    desiredCapabilities.setBrowserName("firefox");
    //    desiredCapabilities.setVersion("94");

    chromeOptions.setExperimentalOption("useAutomationExtension", false);
    chromeOptions.setExperimentalOption(
        "excludeSwitches", Collections.singletonList("enable-automation"));

    setProxy(chromeOptions);
    return new AutoCloseableChromeDriver(chromeOptions);
  }

  protected ChromeOptions setProxy(final ChromeOptions chromeOptions) {
    if (browserConfiguration.getProxyHost() == null) {
      return null;
    }

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

    chromeOptions.setCapability("proxy", proxy);
    return chromeOptions;
  }
}
