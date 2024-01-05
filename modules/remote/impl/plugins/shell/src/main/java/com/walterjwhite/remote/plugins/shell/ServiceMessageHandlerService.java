package com.walterjwhite.remote.plugins.shell;

// import com.walterjwhite.queuedJob.api.model.JobExecution;
// import com.walterjwhite.queue.api.queuedJob.CallableJob;

import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;

public class ServiceMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<ServiceMessage, Void>*/ {
  protected final ShellExecutionService shellExecutionService;

  //  protected JobExecution jobExecution;
  protected ServiceMessage serviceMessage;

  @Inject
  public ServiceMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

  protected String getArguments(final ServiceMessage serviceMessage) {
    return ("sudo service "
        + serviceMessage.getService().getName()
        + " "
        + serviceMessage.getServiceAction().getCommand());
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
  //  public void setEntity(ServiceMessage entity) {
  //    this.serviceMessage = entity;
  //  }
  //
  //  @Override
  //  public ServiceMessage getEntity() {
  //    return serviceMessage;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  //    ShellCommand shellCommand =
  //        new ShellCommand().withCommandLine(getArguments(serviceMessage)).withTimeout(10);
  //    shellExecutionService.run(shellCommand);
  //
  //    return null;
  //  }
}
