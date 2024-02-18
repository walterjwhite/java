package com.walterjwhite.browser.api.model;

import com.walterjwhite.property.api.enumeration.ProxyType;
import com.walterjwhite.browser.api.enumeration.BrowserSSLValidation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.jdo.annotations.PersistenceCapable;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class BrowserConfiguration {

  protected int timeout;

  protected boolean logJavaScript;
  protected boolean logWire;

  protected boolean saveAttachments;

  protected String cachePath;

  protected ProxyType proxyType;

  protected String proxyHost;

  protected Integer proxyPort;

  protected BrowserSSLValidation browserSSLValidation;

  protected int ajaxResourceTimeout;
  protected int ajaxWait;
}
