package com.walterjwhite.identity.email;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.event.MessageCountAdapter;
import jakarta.mail.event.MessageCountEvent;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

public class EmailTokenMessageCountAdapter extends MessageCountAdapter {
  protected final transient BlockingQueue<String> tokenQueue;
  protected final transient Pattern pattern;

  public EmailTokenMessageCountAdapter(
      BlockingQueue<String> tokenQueue, final String subjectRegex) {
    this.tokenQueue = tokenQueue;
    this.pattern = Pattern.compile(subjectRegex);
  }

  @Override
  public void messagesAdded(final MessageCountEvent event) {
    for (Message message : event.getMessages()) {
      try {
        if (messageMatches(message)) {
          tokenQueue.put(message.getSubject());
        }
      } catch (Exception e) {
        throw new RuntimeException("Error processing message", e);
      }
    }
  }

  protected boolean messageMatches(final Message message) throws MessagingException {
    return pattern.matcher(message.getSubject()).matches();
  }
}
