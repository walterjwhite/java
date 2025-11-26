package com.walterjwhite.infrastructure.inject.providers.guice.hk2;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.core.service.StartupAware;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceInjector;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.api.ServiceLocatorFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class GuiceResourceConfig extends ResourceConfig implements StartupAware {
  @Override
  public void startup() {
    final ServiceLocator serviceLocator = ServiceLocatorFactory.getInstance().create("default");
    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
    GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

    guiceBridge.bridgeGuiceInjector(
        ((GuiceInjector) ApplicationHelper.getApplicationInstance().getInjector()).getInjector());
  }

  @Override
  public void close() throws Exception {}
}
