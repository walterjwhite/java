package com.walterjwhite.remote.plugins.shell;

// import com.walterjwhite.queuedJob.api.model.JobExecution;
// import com.walterjwhite.queue.api.queuedJob.CallableJob;

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

  //  @Override
  //  public void onSuccess() {}
  //
  //  @Override
  //  public void onError(Throwable thrown) {}
  //
  //  @Override
  //  public void setJobExecution(JobExecution jobExecution) {}
  //
  //  @Override
  //  public void setEntity(SystemActionMessage entity) {
  //    this.systemActionMessage = entity;
  //  }
  //
  //  @Override
  //  public SystemActionMessage getEntity() {
  //    return systemActionMessage;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  //    ShellCommand shellCommand =
  //        new ShellCommand()
  //            .withCommandLine("sudo " + systemActionMessage.getSystemAction())
  //            .withTimeout(10);
  //    shellExecutionService.run(shellCommand);
  //
  //    return (null);
  //  }
}
