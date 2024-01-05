package com.walterjwhite.ssh.api;

import com.walterjwhite.ssh.api.model.command.SSHCommand;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;

public interface SSHCommandService {
  void execute(SSHCommand... commands) throws Exception;

  void execute(SSHExpectCommand... commands) throws Exception;
}
