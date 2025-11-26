package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;

public class SSHCommandMessageJobRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {
  protected final SSHCommandService sshCommandService;

  @Inject
  public SSHCommandMessageJobRunnable(SSHCommandService sshCommandService) {
    this.sshCommandService = sshCommandService;
  }

  @TimeConstrained
  @Override
  protected void doCall() {
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return null;
  }
}
