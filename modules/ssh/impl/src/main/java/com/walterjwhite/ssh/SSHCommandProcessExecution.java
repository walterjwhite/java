package com.walterjwhite.ssh;

import com.walterjwhite.shell.impl.AbstractProcessExecution;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.connection.channel.direct.Signal;
import net.schmizz.sshj.transport.TransportException;

public class SSHCommandProcessExecution extends AbstractProcessExecution {
  protected final SSHCommand command;
  protected final Session.Command sshCommand;

  public SSHCommandProcessExecution(
      InputStream inputStream,
      InputStream errorStream,
      OutputStream outputStream,
      SSHCommand command,
      Session.Command sshCommand,
      ChronoUnit interruptGracePeriodUnits,
      long interruptGracePeriodValue) {
    super(
        command.getShellCommand(),
        inputStream,
        errorStream,
        outputStream,
        true,
        interruptGracePeriodUnits,
        interruptGracePeriodValue);
    this.command = command;
    this.sshCommand = sshCommand;
  }

  protected void doSetTimeout(int timeoutInSeconds) throws Exception {
    sshCommand.join(timeoutInSeconds, TimeUnit.SECONDS);
  }

  @Override
  protected void kill(Exception e) throws IOException, InterruptedException {
    super.kill(null);

    sshCommand.join(1, TimeUnit.SECONDS);
  }

  @Override
  protected int getReturnCode() {
    return (sshCommand.getExitStatus());
  }

  protected int getTimeout() {
    return (command.getShellCommand().getTimeout());
  }

  public void close() {
    try {
      sshCommand.signal(Signal.KILL);

      super.close();
    } catch (TransportException e) {
      throw new RuntimeException("Error killing ssh command", e);
    }
  }
}
