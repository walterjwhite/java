package com.walterjwhite.ssh.validator;

import com.walterjwhite.ssh.api.SSHCommandOutputValidator;
import com.walterjwhite.ssh.api.model.command.SSHCommand;

public class DefaultSSHCommandOutputValidator implements SSHCommandOutputValidator {
  @Override
  public boolean isValid(SSHCommand sshCommand) {
    return (sshCommand.getShellCommand().getReturnCode() == 0);
  }
}
