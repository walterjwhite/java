package com.walterjwhite.remote.impl.plugins.file.handler;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.remote.impl.plugins.file.message.DeleteFileMessage;
import jakarta.inject.Inject;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class DeleteFileMessageCallable /* extends AbstractRunnable<DeleteFileMessage, Void>*/ {
  protected final FileStorageService fileStorageService;

  @Inject
  public DeleteFileMessageCallable(FileStorageService fileStorageService) {

    this.fileStorageService = fileStorageService;
  }

  protected void deleteFile(DeleteFileMessage deleteFileMessage) throws IOException {
    final java.io.File target = new java.io.File(deleteFileMessage.getTarget());
    if (target.isDirectory()) {
      FileUtils.deleteDirectory(target);
    } else if (target.isFile()) {
      FileUtils.deleteQuietly(target);
    } else {
      throw new IllegalStateException("Target (" + target.getAbsolutePath() + ") already exists.");
    }
  }

}
