package com.walterjwhite.shell.impl.service;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.shell.api.model.*;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import com.walterjwhite.shell.api.service.SystemServiceService;
import com.walterjwhite.shell.impl.property.ServiceTimeout;
import jakarta.inject.Inject;

public class UnixSystemServiceService extends AbstractSingleShellCommandService<ServiceCommand>
    implements SystemServiceService {

  @Inject
  public UnixSystemServiceService(
      ShellCommandBuilder shellCommandBuilder,
      ShellExecutionService shellExecutionService,
      @Property(ServiceTimeout.class) int timeout) {
    super(shellCommandBuilder, shellExecutionService, timeout);
  }

  protected String getCommandLine(ServiceCommand serviceCommand) {
    return "sudo service "
        + serviceCommand.getService() /*.getName()*/
        + " "
        + serviceCommand.getServiceAction().getCommand();
  }

  protected void doAfter(ServiceCommand serviceCommand) {
  }

}
