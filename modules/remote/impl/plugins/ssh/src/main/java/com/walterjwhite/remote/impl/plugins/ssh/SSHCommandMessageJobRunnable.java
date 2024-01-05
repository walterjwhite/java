package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.queue.api.annotation.JobExecutionConfiguration;
import com.walterjwhite.queue.api.enumeration.Durability;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import com.walterjwhite.ssh.api.SSHCommandService;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import jakarta.inject.Inject;

// the timeout for this queuedJob to complete is highly dependent on the command being asked to run
@SubscribeTo(
    eventClass = SSHCommandMessage.class,
    jobExecutionConfiguration = @JobExecutionConfiguration(durability = Durability.Persistent))
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
    //    sshCommandService.execute(entity.getCommand());
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    //    return Duration.of(entity.getCommand().getShellCommand().getTimeout(),
    // ChronoUnit.SECONDS);
    return null;
  }
}
