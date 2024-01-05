package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.remote.api.repository.FindMessagesByTime;
import com.walterjwhite.remote.impl.plugins.heartbeat.HeartbeatMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jakarta.inject.Inject;

/**
 * List all active nodes within the past 5 minutes. IE. checks for heartbeats less than 5 minutes
 * old.
 */
public class ListNodes implements CommandLineHandler {
  protected final Repository repository;

  @Inject
  public ListNodes(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      Repository repository) {
    //    super(shutdownTimeoutInSeconds);

    this.repository = repository;
  }

  @Override
  public void run(final String... arguments) {
    final List<Message> messages =
        (List<Message>) repository.query(new FindMessagesByTime(0, -1, 1, TimeUnit.HOURS));
    for (Message message : messages) {
      if (message instanceof HeartbeatMessage) {
        final HeartbeatMessage heartbeatMessage = ((HeartbeatMessage) message);

        //        final HeartbeatMessageCallable heartbeatMessageHandlerService = new
        // HeartbeatMessageCallable();
        //        heartbeatMessageHandlerService.setEntity(heartbeatMessage);
        //        heartbeatMessageHandlerService.call();

      }
    }
  }
}
