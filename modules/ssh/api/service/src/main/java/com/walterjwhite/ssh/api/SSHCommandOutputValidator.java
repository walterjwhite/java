package com.walterjwhite.ssh.api;

import com.walterjwhite.ssh.api.model.command.SSHCommand;

public interface SSHCommandOutputValidator {
  boolean isValid(SSHCommand sshCommand);
}
