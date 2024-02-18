package com.walterjwhite.email.javamail.async;

import com.walterjwhite.email.javamail.service.JavaMailEmailMessageReadService;
import jakarta.inject.Inject;
import jakarta.mail.Message;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;

public class JavaMailNewMessageCountAdapter extends MessageCountAdapter {
  protected final JavaMailEmailMessageReadService javaMailEmailMessageReadService;

  @Inject
  public JavaMailNewMessageCountAdapter(
      JavaMailEmailMessageReadService javaMailEmailMessageReadService) {
    this.javaMailEmailMessageReadService = javaMailEmailMessageReadService;
  }

  @Override
  public void messagesAdded(final MessageCountEvent event) {
    for (Message message : event.getMessages()) {
      try {

      } catch (Exception e) {
        throw new RuntimeException("Error processing message", e);
      }
    }
  }
}
