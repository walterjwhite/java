package com.walterjwhite.remote.impl.plugins.file.presend;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.queue.api.job.AbstractRunnable;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;

public class FileTransferPreSendMessageJobRunnable extends AbstractRunnable
    implements TimeConstrainedMethodInvocation {
  protected final FileStorageService fileStorageService;

  @Inject
  public FileTransferPreSendMessageJobRunnable(
      FileStorageService fileStorageService) {

    this.fileStorageService = fileStorageService;
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
