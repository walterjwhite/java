package com.walterjwhite.browser.chromedriver;

import com.walterjwhite.browser.api.model.WebSession;
import com.walterjwhite.browser.api.service.SessionAwareWebDriver;
import com.walterjwhite.heartbeat.Heartbeatable;
import com.walterjwhite.heartbeat.annotation.Heartbeat;
import java.time.Duration;
import java.util.concurrent.Semaphore;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

@Slf4j
@Setter
@Getter
public class AutoCloseableChromeDriver extends ChromeDriver
    implements AutoCloseable, SessionAwareWebDriver, Heartbeatable {
  protected transient WebSession webSession;
  protected transient Semaphore semaphore = new Semaphore(1);
  protected final Capabilities webDriverCapabilities;

  public AutoCloseableChromeDriver() {
    webDriverCapabilities = null;
  }

  public AutoCloseableChromeDriver(ChromeDriverService service) {
    super(service);
    webDriverCapabilities = null;
  }

  public AutoCloseableChromeDriver(ChromeOptions options) {
    super(options);
    webDriverCapabilities = options;
  }

  public AutoCloseableChromeDriver(ChromeDriverService service, ChromeOptions options) {
    super(service, options);
    webDriverCapabilities = options;
  }

  @Override
  @SneakyThrows
  public void close() {
    if (webSession == null) {
      return;
    }

    webSession.close();
    webSession = null;
  }

  @Override
  @SneakyThrows
  public void onHeartbeat() {
    if (webSession == null) {
      return;
    }

    try {
      // test our connection
      webSession.getWebDriver().findElement(By.id("43f6e525447611ec88d5f8b156d7eeae"));
    } catch (NoSuchElementException | InvalidElementStateException e) {
      LOGGER.trace("nothing found - expected");
    } catch (WebDriverException e) {
      LOGGER.warn("disconnecting", e);
      cleanup();

      semaphore.release();
    }
  }

  @Override
  public Duration getHeartbeatInterval() {
    return Duration.ofSeconds(1);
  }

  @SneakyThrows
  @Heartbeat
  public void waitToClose() {
    if (webSession.isWaitForUserToClose()) {
      semaphore.acquire();
      semaphore.acquire();
    }
  }

  protected void cleanup() throws InterruptedException {
    if (webSession != null) {
      webSession.close();
      webSession = null;
    }
  }
}
