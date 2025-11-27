package com.walterjwhite.email.impl.service;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailSendRequest;
import com.walterjwhite.email.api.service.EmailSendService;
import com.walterjwhite.email.impl.property.EmailSendRetryAttempts;
import com.walterjwhite.email.impl.property.EmailSendTimeout;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import javax.transaction.Transactional;


public class DefaultEmailSendService implements EmailSendService {
  protected final int emailSendTimeout;
  protected final int emailSendRetryAttempts;

  @Inject
  public DefaultEmailSendService(
      @Property(EmailSendTimeout.class) int emailSendTimeout,
      @Property(EmailSendRetryAttempts.class) int emailSendRetryAttempts) {
    this.emailSendTimeout = emailSendTimeout;
    this.emailSendRetryAttempts = emailSendRetryAttempts;
  }

  @Transactional
  @Override
  public void send(Email email) {
    EmailSendRequest emailSendRequest = buildEmailSendRequest(email);
    send(emailSendRequest);
  }

  protected EmailSendRequest buildEmailSendRequest(Email email) {
    EmailSendRequest emailSendRequest = new EmailSendRequest();
    emailSendRequest.setEmail(email);
    emailSendRequest.setTimeout(emailSendTimeout);
    emailSendRequest.setRetryAttempts(emailSendRetryAttempts);
    return emailSendRequest;
  }

  public void send(EmailSendRequest emailSendRequest) {
  }
}
