package com.walterjwhite.remote.impl;

import com.google.common.eventbus.EventBus;
import com.walterjwhite.job.external.queue.api.ExternalQueueService;
import com.walterjwhite.queue.api.enumeration.QueueType;
import com.walterjwhite.queue.api.model.Queue;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.repository.MessageRepository;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.provider.ClientProvider;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.transaction.Transactional;

public class DefaultMessageWriterService implements MessageWriterService {
  protected final ExternalQueueService externalQueueService;
  protected final EventBus eventBus;
  protected final Provider<MessageRepository> messageRepositoryProvider;
  protected final ClientProvider clientProvider;

  @Inject
  public DefaultMessageWriterService(
      ExternalQueueService externalQueueService,
      EventBus eventBus,
      Provider<MessageRepository> messageRepositoryProvider,
      ClientProvider clientProvider) {

    this.externalQueueService = externalQueueService;
    this.eventBus = eventBus;
    this.messageRepositoryProvider = messageRepositoryProvider;
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
    // message.getRecipients().addAll(clients);
    for (Client client : clients) message.getRecipients().add(client);
  }

  protected void doPreprocess(Message message) {
    // push this to the event bus to do any necessary preprocessing
    eventBus.post(message);
  }

  protected void doWriteToQueue(Message message, Client... clients) throws Exception {
    if (clients == null || clients.length == 0) {
      Queue queue = new Queue("all", QueueType.All);
      externalQueueService.write(queue, message);
    }

    for (Client client : clients) {
      Queue queue = new Queue(Integer.toString(client.getId()), QueueType.Self);
      externalQueueService.write(queue, message);
    }
  }

  @Transactional
  protected Message doPersist(Message message) {
    return (messageRepositoryProvider
        .get()
  }
}
