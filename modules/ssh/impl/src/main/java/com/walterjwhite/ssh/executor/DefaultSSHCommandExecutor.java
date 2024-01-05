package com.walterjwhite.ssh.executor;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.AbstractSSHService;
import com.walterjwhite.ssh.SSHCommandProcessExecution;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import com.walterjwhite.ssh.property.InterruptGracePeriodUnits;
import com.walterjwhite.ssh.property.InterruptGracePeriodValue;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;

public class DefaultSSHCommandExecutor extends AbstractSSHService<SSHCommand> {
  // this doesn't work on guice because Guice requires enum constants to follow Case Caps
  protected final ChronoUnit interruptGracePeriodUnits;
  protected final long interruptGracePeriodValue;

  public DefaultSSHCommandExecutor(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath,
      SSHCommand command,
      @Property(InterruptGracePeriodUnits.class) ChronoUnit interruptGracePeriodUnits,
      @Property(InterruptGracePeriodValue.class) long interruptGracePeriodValue) {
    super(sshPublicKeyPath, command);
    this.interruptGracePeriodUnits = interruptGracePeriodUnits;
    this.interruptGracePeriodValue = interruptGracePeriodValue;
  }

  /**
   * This works fine for commands that return in a finite amount of time. Lastly, it waits until the
   * sshCommand is done before processing the input / output.
   *
   * @throws IOException
   */
  @Override
  protected void doExecute(SSHClient sshClient, Session session)
      throws IOException, InterruptedException {
    session.allocateDefaultPTY();
    try (Session.Command sshCommand = session.exec(command.getShellCommand().getCommandLine())) {
      final SSHCommandProcessExecution sshCommandProcessExecution =
          new SSHCommandProcessExecution(
              sshCommand.getInputStream(),
              sshCommand.getErrorStream(),
              sshCommand.getOutputStream(),
              command,
              sshCommand,
              interruptGracePeriodUnits,
              interruptGracePeriodValue);
      sshCommandProcessExecution.run();
    }
  }
}
