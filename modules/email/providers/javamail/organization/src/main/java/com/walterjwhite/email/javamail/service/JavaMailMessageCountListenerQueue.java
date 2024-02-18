package com.walterjwhite.email.javamail.service;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.javamail.JavaMailMessageToEmailFunction;
import jakarta.mail.event.MessageCountEvent;
import jakarta.mail.event.MessageCountListener;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaMailMessageCountListenerQueue implements MessageCountListener {
  protected final BlockingQueue<Email> blockingQueue;

  protected final transient JavaMailMessageToEmailFunction javaMailMessageToEmailFunction =
      new JavaMailMessageToEmailFunction();

  @Override
  public void messagesAdded(MessageCountEvent e) {
    Arrays.stream(e.getMessages()).map(javaMailMessageToEmailFunction).forEach(email -> put(email));
  }

  protected void put(Email email) {
    try {
      blockingQueue.put(email);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void messagesRemoved(MessageCountEvent e) {

  }
}
