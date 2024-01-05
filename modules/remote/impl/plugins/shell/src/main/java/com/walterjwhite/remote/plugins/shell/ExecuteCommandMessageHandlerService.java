package com.walterjwhite.remote.plugins.shell;

// import com.walterjwhite.queuedJob.api.model.JobExecution;
// import com.walterjwhite.queue.api.queuedJob.CallableJob;

import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.model.CommandError;
import com.walterjwhite.shell.api.model.CommandOutput;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import java.util.List;
import jakarta.inject.Inject;

public class ExecuteCommandMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<ExecuteCommandMessage, Void>*/ {

  protected final ShellExecutionService shellExecutionService;

  protected ExecuteCommandMessage executeCommandMessage;
  //  protected JobExecution jobExecution;

  @Inject
  public ExecuteCommandMessageHandlerService(
      MessageWriterService messageWriterService, ShellExecutionService shellExecutionService) {
    super(messageWriterService);
    this.shellExecutionService = shellExecutionService;
  }

  protected static String getOutput(List<CommandOutput> commandOutputs) {
    final StringBuilder buffer = new StringBuilder();
    for (CommandOutput commandOutput : commandOutputs) {
      buffer.append(commandOutput.getOutput() + "\n");
    }

    return (buffer.toString());
  }

  protected static String getError(List<CommandError> commandErrors) {
    final StringBuilder buffer = new StringBuilder();
    for (CommandError commandError : commandErrors) {
      buffer.append(commandError.getOutput() + "\n");
    }

    return (buffer.toString());
  }

  protected String getArguments(final ExecuteCommandMessage executeCommandMessage) {
    if (executeCommandMessage.getRunAs() != null)
      return ("sudo "
          + executeCommandMessage.getRunAs()
          + " "
          + executeCommandMessage.getCommand());

    return (executeCommandMessage.getCommand());
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
  //  public void setEntity(ExecuteCommandMessage entity) {
  //    this.executeCommandMessage = entity;
  //  }
  //
  //  @Override
  //  public ExecuteCommandMessage getEntity() {
  //    return executeCommandMessage;
  //  }
  //
  //  @Override
  //  public Void call() throws Exception {
  //    ShellCommand shellCommand =
  //        new ShellCommand().withCommandLine(getArguments(executeCommandMessage)).withTimeout(10);
  //    shellExecutionService.run(shellCommand);
  //
  //    if (executeCommandMessage.isCaptureResponse()) {
  //      reply(
  //          executeCommandMessage,
  //          new CommandOutputMessage(
  //              executeCommandMessage.getSender(),
  //              executeCommandMessage.getTimeToLive(),
  //              executeCommandMessage,
  //              getOutput(shellCommand.getOutputs()),
  //              getError(shellCommand.getErrors()),
  //              shellCommand.getReturnCode()));
  //    }
  //
  //    return null;
  //  }
}
