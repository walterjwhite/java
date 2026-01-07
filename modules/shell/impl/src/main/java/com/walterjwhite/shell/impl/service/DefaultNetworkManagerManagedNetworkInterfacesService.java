package com.walterjwhite.shell.impl.service;

import com.google.common.eventbus.EventBus;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;

public
class DefaultNetworkManagerManagedNetworkInterfacesService /*implements NetworkManagerManagedNetworkInterfacesService*/ {

  protected final EventBus eventBus;
  protected final ShellExecutionService shellExecutionService;

  @Inject
  public DefaultNetworkManagerManagedNetworkInterfacesService(
      EventBus eventBus, ShellExecutionService shellExecutionService) {

    this.eventBus = eventBus;
    this.shellExecutionService = shellExecutionService;
  }

}
