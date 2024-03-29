package com.walterjwhite.shell.impl.service;

import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.shell.api.model.*;
import com.walterjwhite.shell.api.service.ShellExecutionService;
import com.walterjwhite.shell.impl.ShellProcessExecution;
import com.walterjwhite.shell.impl.annotation.EntityEnabled;
import com.walterjwhite.shell.impl.property.InterruptGracePeriodUnits;
import com.walterjwhite.shell.impl.property.InterruptGracePeriodValue;
import com.walterjwhite.shell.impl.util.ShellExecutionUtil;
import jakarta.inject.Inject;
import java.io.IOException;
import java.time.temporal.ChronoUnit;

public class DefaultShellExecution implements ShellExecutionService, AutoCloseable {
  protected boolean shutdown;

  protected final ChronoUnit interruptGracePeriodUnits;
  protected final long interruptGracePeriodValue;

  @Inject
  public DefaultShellExecution(
      @Property(InterruptGracePeriodUnits.class) ChronoUnit interruptGracePeriodUnits,
      @Property(InterruptGracePeriodValue.class) long interruptGracePeriodValue) {

    this.interruptGracePeriodUnits = interruptGracePeriodUnits;
    this.interruptGracePeriodValue = interruptGracePeriodValue;
  }

  @EntityEnabled
  @Override
  public ShellCommand run(ShellCommand shellCommand) throws Exception {
    checkIfShutdown();

    doRun(shellCommand);
    return shellCommand;
  }

  protected void checkIfShutdown() {
    if (shutdown) throw new IllegalStateException("Service is shutting down");
  }

  protected void doRun(ShellCommand shellCommand) throws IOException, InterruptedException {
    new ShellProcessExecution(
            setupProcess(shellCommand),
            shellCommand,
            interruptGracePeriodUnits,
            interruptGracePeriodValue)
        .run();
  }

  protected Process setupProcess(ShellCommand shellCommand) throws IOException {
    if (shellCommand instanceof Chrootable) {
      if (shellCommand instanceof FreeBSDJailShellCommand)
        return doFreeBSDJailChrootProcess(shellCommand);

      return (doChrootProcess(shellCommand));
    }
    return (ShellExecutionUtil.doNonChrootProcess(shellCommand));
  }

  protected Process doFreeBSDJailChrootProcess(ShellCommand shellCommand) throws IOException {
    final Chrootable chrootable = (Chrootable) shellCommand;
    doValidate(shellCommand, chrootable);

    if (shellCommand instanceof EnvironmentAware) {
      final ProcessBuilder processBuilder =
          new ProcessBuilder(ShellExecutionUtil.getProcessBuilderChrootCmdLine(chrootable));
      setEnvironment(
          processBuilder, (EnvironmentAware) shellCommand.getShellCommandEnvironmentProperties());
      return processBuilder.start();
    }
    return (Runtime.getRuntime().exec(ShellExecutionUtil.getChrootCmdLine(chrootable)));


  }

  private static void setEnvironment(
      final ProcessBuilder processBuilder, final EnvironmentAware environmentAware) {
    for (final ShellCommandEnvironmentProperty shellCommandEnvironmentProperty :
        environmentAware.getShellCommandEnvironmentProperties()) {
      setEnvironmentProperty(processBuilder, shellCommandEnvironmentProperty);
    }
  }

  private static void setEnvironmentProperty(
      final ProcessBuilder processBuilder,
      final ShellCommandEnvironmentProperty shellCommandEnvironmentProperty) {
    processBuilder
        .environment()
        .put(shellCommandEnvironmentProperty.getKey(), shellCommandEnvironmentProperty.getValue());
  }

  protected Process doChrootProcess(ShellCommand shellCommand) throws IOException {
    final Chrootable chrootable = (Chrootable) shellCommand;
    doValidate(shellCommand, chrootable);

    if (shellCommand instanceof EnvironmentAware) {
      if (shellCommand instanceof EnvironmentAware) {
        final ProcessBuilder processBuilder =
            new ProcessBuilder(ShellExecutionUtil.getProcessBuilderChrootCmdLine(chrootable));
        setEnvironment(processBuilder, shellCommand);
        return processBuilder.start();
      }
    }
    return (Runtime.getRuntime().exec(ShellExecutionUtil.getChrootCmdLine(chrootable)));


  }

  protected void doValidate(final ShellCommand shellCommand, final Chrootable chrootable) {
    if (chrootable.getChrootPath() == null || chrootable.getChrootPath().isEmpty())
      throw new IllegalStateException(
          "Cannot chroot, chroot path is null:" + shellCommand.getCommandLine());
  }

  @Override
  public void close() {
    shutdown = true;
  }
}
