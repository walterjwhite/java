package com.walterjwhite.ssh.api.model.command;

import com.walterjwhite.shell.api.model.ShellCommand;
import com.walterjwhite.ssh.api.model.AbstractSSHEntity;
import com.walterjwhite.ssh.api.model.SSHHost;
import com.walterjwhite.ssh.api.model.SSHUser;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class SSHCommand extends AbstractSSHEntity {
  protected ShellCommand shellCommand;

  public SSHCommand(SSHHost host, SSHUser user, ShellCommand shellCommand) {
    super(host, user);
    this.shellCommand = shellCommand;
  }
}
