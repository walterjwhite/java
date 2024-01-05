package com.walterjwhite.ssh.executor;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.AbstractSSHService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

// @Component
public class ExpectSSHCommandExecutor extends AbstractSSHService<SSHExpectCommand> {
  public ExpectSSHCommandExecutor(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath, SSHExpectCommand command) {
    super(sshPublicKeyPath, command);
  }

  @Override
  protected void doExecute(SSHClient sshClient, Session session) {
    // @NOTE: deprecated for now
    //
    //    CommandExecutionResult shellCommand = new CommandExecutionResult();
    //    sshCommand.setShellCommand(shellCommand);
    //    try (final Session.Shell shell = session.startShell()) {
    //      try (final Expect expect =
    //          CommandExecutionUtil.setupExpectHandler(
    //              applicationEventPublisher,
    //              shellCommand,
    //              sshCommand.getTimeout(),
    //              shell.getInputStream(),
    //              shell.getErrorStream(),
    //              shell.getOutputStream())) {
    //        //expect.sendLine(sshCommand.getCommandLine());
    //        //    SSHExpectScript sshExpectScript =
    //        //        (SSHExpectScript)
    //        //            Class.forName(sshCommand.getCommandExecutionClassName())
    //        //                .getConstructor(SSHClient.class, Session.class)
    //        //                .newInstance(/*sshClient, */ session);
    //        //    sshExpectScript.run();
    //
    //        CommandExecutionUtil.setReturnCode(expect, shellCommand);
    //      }
    //    }
  }
}
