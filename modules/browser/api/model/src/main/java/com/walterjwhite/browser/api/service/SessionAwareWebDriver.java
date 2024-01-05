package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.model.WebSession;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface SessionAwareWebDriver extends WebDriver {
  WebSession getWebSession();

  void setWebSession(WebSession webSession);

  void waitToClose();

  Capabilities getWebDriverCapabilities();
}
