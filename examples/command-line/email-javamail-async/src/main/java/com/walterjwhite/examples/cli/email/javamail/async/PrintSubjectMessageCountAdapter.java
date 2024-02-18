package com.walterjwhite.examples.cli.email.javamail.async;

import jakarta.mail.MessagingException;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintSubjectMessageCountAdapter extends MessageCountAdapter {
  @Override
  public void messagesAdded(final MessageCountEvent e) {
    Arrays.stream(e.getMessages())
        .forEach(
            m -> {
              try {
                LOGGER.info("message subject: {}", m.getSubject());
              } catch (MessagingException messagingException) {
                LOGGER.warn("ERROR", messagingException);
              }
            });
  }
}
