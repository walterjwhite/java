package com.walterjwhite.email.javamail.service;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.api.service.NewEmailSupplier;
import com.walterjwhite.email.javamail.AbstractJavaMailFolderReader;
import com.walterjwhite.email.javamail.service.example.Listener;
import jakarta.mail.MessagingException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import org.eclipse.angus.mail.imap.IMAPFolder;


public class JavaMailNewEmailSupplier extends AbstractJavaMailFolderReader
    implements NewEmailSupplier {
  
  protected transient BlockingQueue<Email> messageCountEventBlockingQueue;
  protected transient JavaMailMessageCountListenerQueue javaMailMessageCountListenerQueue;
  protected transient Listener listener;

  public JavaMailNewEmailSupplier(PrivateEmailAccount privateEmailAccount, String folderName)
      throws MessagingException {
    initialize(privateEmailAccount, folderName);
    initialize();
  }

  private void initialize() {
    messageCountEventBlockingQueue =
        new PriorityBlockingQueue<>(Runtime.getRuntime().availableProcessors() * 2);
    javaMailMessageCountListenerQueue =
        new JavaMailMessageCountListenerQueue(messageCountEventBlockingQueue);
    listener = new Listener();

    folder.addMessageCountListener(javaMailMessageCountListenerQueue);

    listener.start((IMAPFolder) folder);
  }

  @Override
  public Email get() {
    try {
      return messageCountEventBlockingQueue.take();
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();

      throw new IllegalStateException("Interrupted", e);
    }
  }
}
