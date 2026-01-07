package com.walterjwhite.identity.email;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.javamail.JavaMailUtils;
import com.walterjwhite.heartbeat.Heartbeatable;
import jakarta.mail.Folder;
import jakarta.mail.MessagingException;
import jakarta.mail.Store;
import jakarta.mail.event.MessageCountAdapter;
import java.time.Duration;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.imap.IMAPFolder;

@Slf4j
public class ImapIdleThread implements Heartbeatable, Runnable, AutoCloseable {
  protected final MessageCountAdapter messageCountAdapter;
  protected final PrivateEmailAccount privateEmailAccount;
  protected final String folderName;

  protected volatile Store store;
  protected volatile Folder folder;

  public ImapIdleThread(
      MessageCountAdapter messageCountAdapter,
      PrivateEmailAccount privateEmailAccount,
      String folderName)
      throws MessagingException {
    this.messageCountAdapter = messageCountAdapter;
    this.privateEmailAccount = privateEmailAccount;
    this.folderName = folderName;

    store = JavaMailUtils.getStore(JavaMailUtils.getSession(privateEmailAccount));
    folder = store.getFolder(folderName);
    folder.open(Folder.READ_ONLY);

    folder.addMessageCountListener(messageCountAdapter);
  }

  protected void closeStore(Store store) throws MessagingException {
    if (store != null && store.isConnected()) store.close();
  }

  protected void closeFolder(Folder folder) throws MessagingException {
    if (folder != null && folder.isOpen()) {
      folder.close(false);
    }
  }

  @Override
  public void onHeartbeat() {
    try {
      noop();
    } catch (MessagingException e) {
      throw new RuntimeException("Error idling", e);
    }
  }

  protected void noop() throws MessagingException {
    ((IMAPFolder) folder)
        .doCommand(
            p -> {
              p.simpleCommand("NOOP", null);
              return null;
            });
  }

  @Override
  public Duration getHeartbeatInterval() {
    return privateEmailAccount.getIdleTimeout();
  }

  @Override
  public void run() {
    while (true) {
      try {
        ((IMAPFolder) folder).idle();
      } catch (MessagingException e) {
        LOGGER.warn("Error idling", e);
      }
    }
  }

  @Override
  public void close() throws Exception {
    closeFolder(folder);
    closeStore(store);
  }
}
