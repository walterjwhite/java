package com.walterjwhite.examples.cli.email.javamail.async;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.email.javamail.async.ImapIdleThread;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import javax.mail.MessagingException;

public class AsyncJavaMailCommandLineHandler implements CommandLineHandler {
  protected final SerializationService serializationService;
  protected final PrivateEmailAccount privateEmailAccount;

  @Inject
  public AsyncJavaMailCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      SerializationService serializationService, PrivateEmailAccount privateEmailAccount) {
    //    super(shutdownTimeoutInSeconds);
    this.serializationService = serializationService;
    this.privateEmailAccount = privateEmailAccount;
  }

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
