package com.walterjwhite.email.javamail.service.example;

import jakarta.mail.event.StoreEvent;
import jakarta.mail.event.StoreListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaMailStoreListener implements StoreListener {

  @Override
  public void notification(StoreEvent e) {
    LOGGER.info("\n\nreceived event:" + e.getMessageType() + ":" + e.getMessage() + "\n\n");
  }
}
