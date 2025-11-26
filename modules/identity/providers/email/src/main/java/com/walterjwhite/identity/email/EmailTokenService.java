package com.walterjwhite.identity.email;

import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.EmailEmailAccount;
import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailTokenService implements TokenService, TimeConstrainedMethodInvocation {
  protected final BlockingQueue<String> tokenQueue = new ArrayBlockingQueue<>(1);

  protected final EmailSendService emailSendService;
  protected final EmailTokenConfiguration emailTokenConfiguration;
  protected transient EmailTokenMessageCountAdapter emailTokenMessageCountAdapter;
  protected transient Thread imapIdleThread;

  @Sensitive
  @TimeConstrained
  public String get(final String helpText) throws Exception {
    emailTokenMessageCountAdapter =
        new EmailTokenMessageCountAdapter(tokenQueue, emailTokenConfiguration.getSubjectRegex());
    imapIdleThread =
        new Thread(
            new ImapIdleThread(
                emailTokenMessageCountAdapter,
                emailTokenConfiguration.getEmailAccount(),
                emailTokenConfiguration.getFolderName()));

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
    return Email.builder()
        .from(emailTokenConfiguration.getEmailAccount())
        .subject(subject)
        .body(message)
        .build();
  }

  public static Set<EmailEmailAccount> buildEmailAccountLinkage(
      final EmailRecipientType emailRecipientType, final EmailAccount... emailAccounts) {
    final Set<EmailEmailAccount> emailEmailAccounts = new HashSet<>();
    for (EmailAccount emailAccount : emailAccounts) {
      emailEmailAccounts.add(new EmailEmailAccount(emailAccount, null, emailRecipientType));
    }

    return emailEmailAccounts;
  }
}
