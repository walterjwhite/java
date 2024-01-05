package com.walterjwhite.remote.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

/** Write messages out to Amazon SQS (or whatever other provider is configured). */
@NoArgsConstructor(onConstructor_ = @Inject)
public class MessageWriter implements CommandLineHandler {

  //  @Inject
  //  public MessageWriter(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(final String... arguments) throws Exception {
    // daemon should already be running
    // wait 60 seconds for new messages
    Thread.sleep(60000);
  }
}
