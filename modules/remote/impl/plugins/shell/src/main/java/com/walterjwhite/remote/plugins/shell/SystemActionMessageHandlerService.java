package com.walterjwhite.remote.plugins.shell;


import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;

public class SystemActionMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<SystemActionMessage, Void>*/ {
  protected final ShellExecutionService shellExecutionService;

  protected SystemActionMessage systemActionMessage;

  @Inject
  public SystemActionMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

}
