package com.walterjwhite.browser.api.model;

import com.walterjwhite.browser.api.property.BrowserSSLValidation;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
public class BrowserConfiguration {

  protected int timeout;

  protected boolean logJavaScript;
  protected boolean logWire;

  protected boolean saveAttachments;

  protected String cachePath;

  protected Optional<String> proxyType;

  protected Optional<String> proxyHost;

  protected Optional<Integer> proxyPort;

  protected BrowserSSLValidation browserSSLValidation;

  protected int ajaxResourceTimeout;
  protected int ajaxWait;
}
