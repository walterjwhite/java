package com.walterjwhite.browser.api.model;

import com.walterjwhite.browser.api.service.SessionAwareWebDriver;
import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import com.walterjwhite.keep_alive.KeepAlive;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@NoArgsConstructor
public class WebSession extends BrowserUserConfiguration implements KeepAlive {
  protected LocalDateTime startDateTime = LocalDateTime.now();

  protected String websiteIdentifier;
  protected boolean waitForUserToClose;

  protected transient SessionAwareWebDriver webDriver;

  public void close() throws InterruptedException {
    if (webDriver == null) {
      return;
    }

    webDriver.setWebSession(null);
    webDriver.quit();
    webDriver = null;
  }
}
