package com.walterjwhite.email.modules.javamail.service.example;

import javax.mail.event.StoreEvent;
import javax.mail.event.StoreListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaMailStoreListener implements StoreListener {

  @Override
  public void notification(StoreEvent e) {
    LOGGER.info("\n\nreceived event:" + e.getMessageType() + ":" + e.getMessage() + "\n\n");
  }
}
