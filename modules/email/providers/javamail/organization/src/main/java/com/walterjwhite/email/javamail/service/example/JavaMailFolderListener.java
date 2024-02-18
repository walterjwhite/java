package com.walterjwhite.email.javamail.service.example;

import jakarta.mail.event.FolderEvent;
import jakarta.mail.event.FolderListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaMailFolderListener implements FolderListener {

  @Override
  public void folderCreated(FolderEvent e) {
    LOGGER.info("folderCreated:" + e.getFolder().getName());
  }

  @Override
  public void folderDeleted(FolderEvent e) {
    LOGGER.info("folderDeleted:" + e.getFolder().getName());
  }

  @Override
  public void folderRenamed(FolderEvent e) {
    LOGGER.info("folderRenamed:" + e.getFolder().getName());
  }
}
