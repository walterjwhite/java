package com.walterjwhite.identity.email;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.email.javamail.async.ImapIdleThread;
import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import jakarta.inject.Inject;
import javax.mail.MessagingException;

/** Token is retrieved via email ... */
public class EmailTokenService implements TokenService, TimeConstrainedMethodInvocation {
  protected final BlockingQueue<String> tokenQueue = new ArrayBlockingQueue<>(1);

  protected final EmailSendService emailSendService;
  protected final EmailTokenConfiguration emailTokenConfiguration;
  protected final EmailTokenMessageCountAdapter emailTokenMessageCountAdapter;
  protected final transient Thread imapIdleThread;

  @Inject
  public EmailTokenService(
      final EmailSendService emailSendService,
      final EmailTokenConfiguration emailTokenConfiguration)
      throws MessagingException {
    this.emailSendService = emailSendService;
    this.emailTokenConfiguration = emailTokenConfiguration;
    emailTokenMessageCountAdapter =
        new EmailTokenMessageCountAdapter(tokenQueue, emailTokenConfiguration.getSubjectRegex());
    imapIdleThread =
        new Thread(
            new ImapIdleThread(
                emailTokenMessageCountAdapter,
                emailTokenConfiguration.getEmailAccount(),
                emailTokenConfiguration.getFolderName()));
  }

  @TimeConstrained
  public String get(final String helpText) throws InterruptedException {
    imapIdleThread.start();
    return tokenQueue.take();
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return emailTokenConfiguration.getTokenTimeout();
  }

  @Override
  public void onSuccess(String message, Object... arguments) throws Exception {
    emailSendService.send(build("successful", message));
  }

  public void onException(final Exception e) throws Exception {
    emailSendService.send(build("error", e.getMessage()));
  }

  protected Email build(final String subject, final String message) {
    return new Email(
        Set.of(
            new EmailAccount()
                .withEmailAddress(emailTokenConfiguration.getEmailAccount().getUsername())),
        emailTokenConfiguration.getEmailAccount(),
        subject,
        message);
  }
}
