package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.ConfigurableProperty;
import com.walterjwhite.property.api.property.MappedEnvironmentProperty;
import com.walterjwhite.property.api.property.NoProxy;
import com.walterjwhite.property.api.property.ProxyHost;
import com.walterjwhite.property.api.property.ProxyPort;

@Optional
public enum SystemProxy implements MappedEnvironmentProperty {
  HttpProxy("http_proxy", ProxyHost.class, ProxyPort.class) {
    protected String getValue(
        String environmentValue, Class<? extends ConfigurableProperty> propertyClass) {
      if (environmentValue == null) return null;

      if (ProxyHost.class.equals(propertyClass))
        return (environmentValue.substring(
            environmentValue.indexOf("://") + 3, environmentValue.lastIndexOf(":")));

      return (environmentValue.substring(environmentValue.lastIndexOf(":") + 1));
    }
  },
  HttpsProxy("https_proxy", ProxyHost.class, ProxyPort.class) {
    protected String getValue(
        String environmentValue, Class<? extends ConfigurableProperty> propertyClass) {
      if (environmentValue == null) return null;

      if (ProxyHost.class.equals(propertyClass))
        return (environmentValue.substring(
            environmentValue.indexOf("://") + 3, environmentValue.lastIndexOf(":")));

      return (environmentValue.substring(environmentValue.lastIndexOf(":") + 1));
    }
  },
  NoProxy("no_proxy", NoProxy.class) {
    protected String getValue(
        String environmentValue, Class<? extends ConfigurableProperty> propertyClass) {
      return (environmentValue);
    }
  };

  protected final String key;
  protected final Class<? extends ConfigurableProperty>[] supportedProperties;

  SystemProxy(String key, Class<? extends ConfigurableProperty>... supportedProperties) {
    this.key = key;
    this.supportedProperties = supportedProperties;
  }

  protected abstract String getValue(
      final String environmentValue, Class<? extends ConfigurableProperty> propertyClass);

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public Class<? extends ConfigurableProperty>[] getSupportedProperties() {
    return supportedProperties;
  }

  @Override
  public String getValue(Class<? extends ConfigurableProperty> propertyClass) {
    return getValue(System.getenv().get(getKey()), propertyClass);
  }
}
