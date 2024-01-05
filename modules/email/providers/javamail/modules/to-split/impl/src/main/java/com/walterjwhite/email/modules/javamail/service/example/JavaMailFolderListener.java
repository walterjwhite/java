package com.walterjwhite.email.modules.javamail.service.example;

import javax.mail.event.FolderEvent;
import javax.mail.event.FolderListener;
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
