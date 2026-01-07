package com.walterjwhite.ssh.executor;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.AbstractSSHService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

public class ExpectSSHCommandExecutor extends AbstractSSHService<SSHExpectCommand> {
  public ExpectSSHCommandExecutor(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath, SSHExpectCommand command) {
    super(sshPublicKeyPath, command);
  }

  @Override
  protected void doExecute(SSHClient sshClient, Session session) {
  }
}
