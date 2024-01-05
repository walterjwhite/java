package com.walterjwhite.ssh.service;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.ssh.api.SSHPublicKeyPath;
import com.walterjwhite.ssh.api.model.command.SSHCommand;
import com.walterjwhite.ssh.api.model.command.SSHExpectCommand;
import com.walterjwhite.ssh.executor.DefaultSSHCommandExecutor;
import com.walterjwhite.ssh.executor.ExpectSSHCommandExecutor;
import com.walterjwhite.ssh.property.InterruptGracePeriodUnits;
import com.walterjwhite.ssh.property.InterruptGracePeriodValue;
import java.time.temporal.ChronoUnit;
import jakarta.inject.Inject;

public class DefaultSSHCommandService implements SSHCommandService, AutoCloseable {
  protected final String sshPublicKeyPath;
  protected final ChronoUnit interruptGracePeriodUnits;
  protected final long interruptGracePeriodValue;

  protected boolean shutdown = false;

  @Inject
  public DefaultSSHCommandService(
      @Property(SSHPublicKeyPath.class) final String sshPublicKeyPath,
      @Property(InterruptGracePeriodUnits.class) ChronoUnit interruptGracePeriodUnits,
      @Property(InterruptGracePeriodValue.class) long interruptGracePeriodValue) {

    this.sshPublicKeyPath = sshPublicKeyPath;
    this.interruptGracePeriodUnits = interruptGracePeriodUnits;
    this.interruptGracePeriodValue = interruptGracePeriodValue;
  }

  public void execute(SSHCommand... commands) throws Exception {

    for (SSHCommand command : commands) execute(command);
  }

  protected void execute(SSHCommand command) throws Exception {
    checkIfShutdown();
    new DefaultSSHCommandExecutor(
            sshPublicKeyPath, command, interruptGracePeriodUnits, interruptGracePeriodValue)
        .execute();
  }

  public void execute(SSHExpectCommand... commands) throws Exception {
    for (SSHExpectCommand command : commands) execute(command);
  }

  protected void execute(SSHExpectCommand command) throws Exception {
    checkIfShutdown();
    new ExpectSSHCommandExecutor(sshPublicKeyPath, command).execute();
  }

  protected void checkIfShutdown() {
    if (shutdown)
      throw new IllegalStateException("Unable to run new commands as the system is shutting down.");
  }

  @Override
  public void close() {
    shutdown = true;
  }
}
