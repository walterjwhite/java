package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.repository.MessageRepository;
import jakarta.inject.Inject;

/**
 * List all active nodes within the past 5 minutes. IE. checks for heartbeats less than 5 minutes
 * old.
 */
public class MessageReader implements CommandLineHandler {
  protected final MessageRepository messageRepository;

  @Inject
  public MessageReader(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      MessageRepository messageRepository) {
    //    super(shutdownTimeoutInSeconds);
    this.messageRepository = messageRepository;
  }

  @Override
  public void run(final String... arguments) {
    for (Message message : messageRepository.findWithinThePastHour()) {
      displayMessage(message);
    }
  }

  protected void displayMessage(Message message) {}
}
