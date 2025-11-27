package com.walterjwhite.remote.impl;

import com.google.common.eventbus.EventBus;
import com.walterjwhite.queue.api.enumeration.QueueType;
import com.walterjwhite.queue.api.model.Queue;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.provider.ClientProvider;
import jakarta.inject.Inject;
import javax.transaction.Transactional;

public class DefaultMessageWriterService implements MessageWriterService {
  protected final EventBus eventBus;
  protected final ClientProvider clientProvider;

  @Inject
  public DefaultMessageWriterService(
      EventBus eventBus,
      ClientProvider clientProvider) {

    this.eventBus = eventBus;
    this.clientProvider = clientProvider;
  }

  @Override
  public void write(Message message, Client... clients) throws Exception {
    prepareMessage(message, clients);
    message = doPersist(message);
    doPreprocess(message);
    doWriteToQueue(message, clients);
  }

  protected void prepareMessage(Message message, Client... clients) {
    message.setSender(clientProvider.get());
    for (Client client : clients) message.getRecipients().add(client);
  }

  protected void doPreprocess(Message message) {
    eventBus.post(message);
  }

  protected void doWriteToQueue(Message message, Client... clients) throws Exception {
    if (clients == null || clients.length == 0) {
      Queue queue = new Queue("all", QueueType.All);
    }

    for (Client client : clients) {
      Queue queue = new Queue(Integer.toString(client.getId()), QueueType.Self);
    }
  }

  @Transactional
  protected Message doPersist(Message message) {
    return null;
  }
}
