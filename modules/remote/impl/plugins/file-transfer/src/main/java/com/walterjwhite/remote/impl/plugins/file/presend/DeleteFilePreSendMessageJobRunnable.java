package com.walterjwhite.remote.impl.plugins.file.presend;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.remote.impl.plugins.file.property.DeleteFileTimeoutUnits;
import com.walterjwhite.remote.impl.plugins.file.property.DeleteFileTimeoutValue;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class DeleteFilePreSendMessageJobRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {
  protected final FileStorageService fileStorageService;
  protected final ChronoUnit deleteFileTimeoutUnits;
  protected final long deleteFileTimeoutValue;

  @Inject
  public DeleteFilePreSendMessageJobRunnable(
      FileStorageService fileStorageService,
      @Property(DeleteFileTimeoutUnits.class) final ChronoUnit deleteFileTimeoutUnits,
      @Property(DeleteFileTimeoutValue.class) final long deleteFileTimeoutValue) {

    this.fileStorageService = fileStorageService;
    this.deleteFileTimeoutUnits = deleteFileTimeoutUnits;
    this.deleteFileTimeoutValue = deleteFileTimeoutValue;
  }

  @TimeConstrained
  @Override
  protected void doCall() {

  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return Duration.of(deleteFileTimeoutValue, deleteFileTimeoutUnits);
  }
}
