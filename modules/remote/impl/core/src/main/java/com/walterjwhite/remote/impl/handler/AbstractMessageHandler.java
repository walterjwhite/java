package com.walterjwhite.remote.impl.handler;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.service.MessageWriterService;

public abstract class AbstractMessageHandler {
  protected final MessageWriterService messageWriterService;

  protected AbstractMessageHandler(MessageWriterService messageWriterService) {

    this.messageWriterService = messageWriterService;
  }

  protected void reply(Message request, Message message) throws Exception {
    if (message != null) {
      message.getRecipients().add(request.getSender());
      messageWriterService.write(
          message, message.getRecipients().toArray(new Client[message.getRecipients().size()]));
    }
  }
}
