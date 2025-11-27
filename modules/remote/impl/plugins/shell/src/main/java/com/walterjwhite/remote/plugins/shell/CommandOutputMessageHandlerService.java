package com.walterjwhite.remote.plugins.shell;


import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import jakarta.inject.Inject;

public class CommandOutputMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<CommandOutputMessage, Void>*/ {

  protected CommandOutputMessage commandOutputMessage;


  @Inject
  public CommandOutputMessageHandlerService(MessageWriterService messageWriterService) {
    super(messageWriterService);
  }

}
