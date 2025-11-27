package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.property.JavaEnvironmentProperty;
import com.walterjwhite.property.api.property.NoProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Optional
@RequiredArgsConstructor
@Getter
public enum JavaProxy implements JavaEnvironmentProperty {
  HttpProxyHost("http.proxyHost", ProxyHost.class),
  HttpProxyPort("http.proxyPort", ProxyPort.class),
  HttpsProxyHost("https.proxyHost", ProxyHost.class),
  HttpsProxyPort("https.proxyPort", ProxyPort.class),
  NoProxy("http.nonProxyHosts", NoProxy.class);

  protected final String key;
  protected final Class<? extends ConfigurableProperty> propertyKey;
}
