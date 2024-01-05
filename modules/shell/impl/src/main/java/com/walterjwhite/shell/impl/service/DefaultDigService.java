package com.walterjwhite.shell.impl.service;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.shell.api.model.CommandOutput;
import com.walterjwhite.shell.api.model.IPAddress;
import com.walterjwhite.shell.api.model.dig.DigRequest;
import com.walterjwhite.shell.api.model.dig.DigRequestIPAddress;
import com.walterjwhite.shell.api.service.DigService;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import com.walterjwhite.shell.impl.property.DigTimeout;
import java.time.LocalDateTime;
import jakarta.inject.Inject;

public class DefaultDigService extends AbstractSingleShellCommandService<DigRequest>
    implements DigService {
  @Inject
  public DefaultDigService(
      ShellCommandBuilder shellCommandBuilder,
      ShellExecutionService shellExecutionService,
      @Property(DigTimeout.class) int timeout) {
    super(shellCommandBuilder, shellExecutionService, timeout);
  }

  protected void doAfter(DigRequest digRequest) {
    for (final CommandOutput commandOutput : digRequest.getShellCommand().getOutputs()) {
      digRequest.getDigRequestIPAddresses().add(getIPAddress(commandOutput, digRequest));
    }
  }

  protected DigRequestIPAddress getIPAddress(CommandOutput commandOutput, DigRequest digRequest) {
    return new DigRequestIPAddress(
        LocalDateTime.now(), new IPAddress(commandOutput.getOutput()), digRequest);
  }

  protected String getCommandLine(DigRequest digRequest) {
    return "dig -4 +short " + digRequest.getNetworkDiagnosticTest().getFqdn();
  }
}
