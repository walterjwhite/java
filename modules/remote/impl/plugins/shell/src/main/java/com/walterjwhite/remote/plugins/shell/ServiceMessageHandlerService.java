package com.walterjwhite.remote.plugins.shell;


import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;

public class ServiceMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<ServiceMessage, Void>*/ {
  protected final ShellExecutionService shellExecutionService;

  protected ServiceMessage serviceMessage;

  @Inject
  public ServiceMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

  protected String getArguments(final ServiceMessage serviceMessage) {
    return ("sudo service "
        + serviceMessage.getService() /*.getName()*/
        + " "
        + serviceMessage.getServiceAction().getCommand());
  }

}
