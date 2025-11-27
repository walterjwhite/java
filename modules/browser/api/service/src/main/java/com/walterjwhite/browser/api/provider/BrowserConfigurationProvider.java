package com.walterjwhite.browser.api.provider;

import com.walterjwhite.browser.api.enumeration.BrowserSSLValidation;
import com.walterjwhite.browser.api.model.BrowserConfiguration;
import com.walterjwhite.browser.api.property.AjaxResourceTimeout;
import com.walterjwhite.browser.api.property.AjaxWait;
import com.walterjwhite.browser.api.property.BrowserCachePath;
import com.walterjwhite.browser.api.property.LogJavascript;
import com.walterjwhite.browser.api.property.LogWire;
import com.walterjwhite.browser.api.property.NetworkTimeout;
import com.walterjwhite.browser.api.property.SaveAttachments;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.enumeration.ProxyType;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class BrowserConfigurationProvider implements Provider<BrowserConfiguration> {
  protected final BrowserConfiguration configuration;

  @Inject
  public BrowserConfigurationProvider(
      @Property(BrowserCachePath.class) String browserCachePath,
      @Property(ProxyHost.class) String proxyHost,
      @Property(ProxyPort.class) Integer proxyPort,
      @Property(ProxyType.class) ProxyType proxyType,
      @Property(SaveAttachments.class) boolean saveAttachments,
      @Property(NetworkTimeout.class) int timeout,
      @Property(BrowserSSLValidation.class) BrowserSSLValidation browserSSLValidation,
      @Property(AjaxResourceTimeout.class) int ajaxResourceTimeout,
      @Property(AjaxWait.class) int ajaxWait,
      @Property(LogWire.class) boolean logWire,
      @Property(LogJavascript.class) boolean logJavascript,
      @Property(Debug.class) boolean debug) {

    if (debug) {
      logWire = true;
      logJavascript = true;
    }

    configuration =
        new BrowserConfiguration(
            timeout,
            logJavascript,
            logWire,
            saveAttachments,
            browserCachePath,
            proxyType,
            proxyHost,
            proxyPort,
            browserSSLValidation,
            ajaxResourceTimeout,
            ajaxWait);
  }

  @Override
  public BrowserConfiguration get() {
    return configuration;
  }
}
