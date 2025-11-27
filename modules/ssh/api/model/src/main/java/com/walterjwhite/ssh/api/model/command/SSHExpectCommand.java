package com.walterjwhite.ssh.api.model.command;

import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class SSHExpectCommand extends SSHCommand {

  protected String commandExecutionClassName;

  public SSHExpectCommand(
      SSHHost host, SSHUser user, ShellCommand shellCommand, String commandExecutionClassName) {
    super(host, user, shellCommand);
    this.commandExecutionClassName = commandExecutionClassName;
  }
}
