package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.property.JavaEnvironmentProperty;
import com.walterjwhite.property.api.property.NoProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;

/**
 * If a property source (could be environment, properties, manifest, etc.) has a proxy configured
 * and the system property target is in the classpath, then this will write those values back to the
 * System settings such that any HTTP calls should use the proxy as configured.
 */
@Optional
public enum JavaProxy implements JavaEnvironmentProperty {
  HttpProxyHost("http.proxyHost", ProxyHost.class),
  HttpProxyPort("http.proxyPort", ProxyPort.class),
  HttpsProxyHost("https.proxyHost", ProxyHost.class),
  HttpsProxyPort("https.proxyPort", ProxyPort.class),
  NoProxy("http.nonProxyHosts", NoProxy.class);

  protected final String key;
  protected final Class<? extends ConfigurableProperty> propertyKey;

  JavaProxy(String key, Class<? extends ConfigurableProperty> propertyKey) {
    this.key = key;
    this.propertyKey = propertyKey;
  }

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public Class<? extends ConfigurableProperty> getPropertyKey() {
    return propertyKey;
  }
}
