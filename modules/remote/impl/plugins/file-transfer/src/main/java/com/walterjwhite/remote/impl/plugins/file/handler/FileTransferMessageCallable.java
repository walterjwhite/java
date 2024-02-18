package com.walterjwhite.remote.impl.plugins.file.handler;

import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.remote.impl.plugins.file.message.FileTransferMessage;
import jakarta.inject.Inject;


public class FileTransferMessageCallable
 {
  protected final FileStorageService fileStorageService;

  @Inject
  public FileTransferMessageCallable(FileStorageService fileStorageService) {

    this.fileStorageService = fileStorageService;
  }

  protected void moveFile(FileTransferMessage fileTransferMessage) {
    final java.io.File target = new java.io.File(fileTransferMessage.getTarget());
    if (!target.exists() || target.isDirectory()) {
      final java.io.File source = new java.io.File(fileTransferMessage.getFile().getSource());
      source.renameTo(target);
    } else {
      throw new IllegalStateException("Target (" + target.getAbsolutePath() + ") already exists.");
    }
  }













}
