package com.walterjwhite.remote.impl.plugins.file.presend;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.queue.event.annotation.SubscribeTo;
import com.walterjwhite.queue.impl.worker.property.JobExecutionHeartbeatTimeoutUnits;
import com.walterjwhite.queue.impl.worker.property.JobExecutionHeartbeatTimeoutValue;
import com.walterjwhite.remote.impl.plugins.file.message.FileTransferMessage;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import jakarta.inject.Inject;

@SubscribeTo(eventClass = FileTransferMessage.class)
public class FileTransferPreSendMessageJobRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {
  protected final FileStorageService fileStorageService;

  @Inject
  public FileTransferPreSendMessageJobRunnable(
      @Property(JobExecutionHeartbeatTimeoutValue.class) long heartbeatIntervalValue,
      @Property(JobExecutionHeartbeatTimeoutUnits.class) ChronoUnit heartbeatIntervalUnits,
      FileStorageService fileStorageService) {

    this.fileStorageService = fileStorageService;
  }

  @TimeConstrained
  @Override
  protected void doCall() {
    //    fileStorageService.put(entity.getFile());
  }

  /**
   * Factor in the size of the file for the timeout here ... Assumption, 1024 kB / s (1kB /
   * millisecond) / (1 MB / s)
   *
   * @return allowed execution duration
   */
  @Override
  public Duration getAllowedExecutionDuration() {
    //    final File source = new File(entity.getFile().getSource());
    //    return Duration.ofMillis(source.length() / 1024);
    return null;
  }
}
