package com.walterjwhite.infrastructure.inject.providers.guice.hk2;

import com.walterjwhite.infrastructure.inject.core.NoInject;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceInjector;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

public class GuiceResourceConfig extends ResourceConfig implements NoInject {
  @Inject
  public GuiceResourceConfig(
      final ServiceLocator serviceLocator, final ServletContext servletContext) {
    GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
    GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);

    guiceBridge.bridgeGuiceInjector(
        ((GuiceInjector) ApplicationHelper.getApplicationInstance().getInjector()).getInjector());
  }
}
