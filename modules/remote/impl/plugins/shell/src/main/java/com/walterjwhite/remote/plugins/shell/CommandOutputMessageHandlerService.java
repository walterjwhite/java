package com.walterjwhite.remote.plugins.shell;

// import com.walterjwhite.queuedJob.api.model.JobExecution;
// import com.walterjwhite.queue.api.queuedJob.CallableJob;

import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import jakarta.inject.Inject;

/** Simply logs the command output message to the configured logger. */
public class CommandOutputMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<CommandOutputMessage, Void>*/ {

  protected CommandOutputMessage commandOutputMessage;
  //  protected JobExecution jobExecution;

  @Inject
  public CommandOutputMessageHandlerService(MessageWriterService messageWriterService) {
    super(messageWriterService);
  }

  //  @Override
  //  public void onSuccess() {}
  //
  //  @Override
  //  public void onError(Throwable thrown) {}
  //
  //  @Override
  //  public void setJobExecution(JobExecution jobExecution) {
  //    this.jobExecution = jobExecution;
  //  }
  //
  //  @Override
  //  public void setEntity(CommandOutputMessage entity) {
  //    this.commandOutputMessage = entity;
  //  }
  //
  //  @Override
  //  public CommandOutputMessage getEntity() {
  //    return commandOutputMessage;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  //    return null;
  //  }
}
