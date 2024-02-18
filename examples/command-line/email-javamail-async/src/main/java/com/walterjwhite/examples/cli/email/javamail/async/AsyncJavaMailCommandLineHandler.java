package com.walterjwhite.examples.cli.email.javamail.async;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.javamail.async.ImapIdleThread;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class AsyncJavaMailCommandLineHandler implements CommandLineHandler {
  protected final SerializationService serializationService;
  protected final PrivateEmailAccount privateEmailAccount;

  @Override
  public void run(String... arguments) throws InterruptedException, MessagingException {
    final Thread imapIdleThread =
        new Thread(
            new ImapIdleThread(
                new PrintSubjectMessageCountAdapter(), privateEmailAccount, "INBOX"));
    imapIdleThread.start();

    imapIdleThread.join();
  }
}
