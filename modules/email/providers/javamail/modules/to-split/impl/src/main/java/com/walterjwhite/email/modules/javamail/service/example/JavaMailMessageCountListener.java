package com.walterjwhite.email.modules.javamail.service.example;

import javax.mail.event.MessageCountEvent;
import javax.mail.event.MessageCountListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaMailMessageCountListener implements MessageCountListener {

  @Override
  public void messagesAdded(MessageCountEvent e) {
    LOGGER.info("messagesAdded:" + e.getType());
  }

  @Override
  public void messagesRemoved(MessageCountEvent e) {
    LOGGER.info("messagesRemoved:" + e.getType());
  }
}
