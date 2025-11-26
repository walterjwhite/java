package com.walterjwhite.property.api.enumeration;

import com.walterjwhite.property.api.annotation.Optional;
import com.walterjwhite.property.api.property.*;

@Optional
public enum SystemProxy implements MappedEnvironmentProperty {
  @Optional
  HttpProxy("http_proxy", ProxyHost.class, ProxyPort.class) {
    protected String getValue(
        String environmentValue, Class<? extends ConfigurableProperty> propertyClass) {
      if (environmentValue == null) {
        return null;
      }

      if (ProxyHost.class.equals(propertyClass)) {
        return (environmentValue.substring(
            environmentValue.indexOf("://") + 3, environmentValue.lastIndexOf(":")));
      }

      return (environmentValue.substring(environmentValue.lastIndexOf(":") + 1));
    }
  },
  @Optional
  HttpsProxy("https_proxy", ProxyHost.class, ProxyPort.class) {
    protected String getValue(
        String environmentValue, Class<? extends ConfigurableProperty> propertyClass) {
      if (environmentValue == null) {
        return null;
      }

      if (ProxyHost.class.equals(propertyClass)) {
        return (environmentValue.substring(
            environmentValue.indexOf("://") + 3, environmentValue.lastIndexOf(":")));
      }

      return (environmentValue.substring(environmentValue.lastIndexOf(":") + 1));
    }
  },
  @Optional
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
