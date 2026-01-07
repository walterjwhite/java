package com.walterjwhite.vaadin.data.pipe;

import com.vaadin.server.DeploymentConfiguration;
import com.vaadin.shared.communication.PushMode;
import java.util.Properties;

public class VaadinDeploymentConfiguration implements DeploymentConfiguration {
  @Override
  public boolean isProductionMode() {
    return false;
  }

  @Override
  public boolean isXsrfProtectionEnabled() {
    return false;
  }

  @Override
  public boolean isSyncIdCheckEnabled() {
    return false;
  }

  @Override
  public int getResourceCacheTime() {
    return 0;
  }

  @Override
  public int getHeartbeatInterval() {
    return 0;
  }

  @Override
  public boolean isSendUrlsAsParameters() {
    return false;
  }

  @Override
  public boolean isCloseIdleSessions() {
    return false;
  }

  @Override
  public PushMode getPushMode() {
    return PushMode.AUTOMATIC;
  }

  @Override
  public Properties getInitParameters() {
    return null;
  }

  @Override
  public String getApplicationOrSystemProperty(String propertyName, String defaultValue) {
    return null;
  }

  @Override
  public String getUIClassName() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public String getUIProviderClassName() {
    return null;
  }

  @Override
  public String getWidgetset(String defaultValue) {
    return null;
  }

  @Override
  public String getResourcesPath() {
    return null;
  }

  @Override
  public String getClassLoaderName() {
    return null;
  }
}
