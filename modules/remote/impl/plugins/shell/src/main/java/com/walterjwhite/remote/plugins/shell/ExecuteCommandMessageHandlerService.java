package com.walterjwhite.remote.plugins.shell;


import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.handler.AbstractMessageHandler;
import com.walterjwhite.shell.api.model.CommandError;
import com.walterjwhite.shell.api.model.CommandOutput;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import jakarta.inject.Inject;
import java.util.List;

public class ExecuteCommandMessageHandlerService extends AbstractMessageHandler
/*implements CallableJob<ExecuteCommandMessage, Void>*/ {

  protected final ShellExecutionService shellExecutionService;

  protected ExecuteCommandMessage executeCommandMessage;


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

}
