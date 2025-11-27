package com.walterjwhite.browser.api.service;

import com.walterjwhite.browser.api.property.BrowserMouseMaximumDelay;
import com.walterjwhite.browser.api.property.BrowserMouseMinimumDelay;
import com.walterjwhite.browser.api.property.BrowserTypeMaximumDelay;
import com.walterjwhite.browser.api.property.BrowserTypeMinimumDelay;
import com.walterjwhite.browser.api.user.BrowserUserConfiguration;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class BrowserUserConfigurationProvider implements Provider<BrowserUserConfiguration> {
  protected final transient BrowserUserConfiguration browserUserConfiguration;

  @Inject
  public BrowserUserConfigurationProvider(
      @Property(BrowserMouseMinimumDelay.class) long browserMouseMinimumDelay,
      @Property(BrowserMouseMaximumDelay.class) long browserMouseMaximumDelay,
      @Property(BrowserTypeMinimumDelay.class) long browserTypeMinimumDelay,
      @Property(BrowserTypeMaximumDelay.class) long browserTypeMaximumDelay) {
    browserUserConfiguration = null;
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public BrowserUserConfiguration get() {
    return browserUserConfiguration;
  }
}
