package com.walterjwhite.remote.impl.plugins.email;

import com.walterjwhite.email.api.service.EmailSendService;
import jakarta.inject.Inject;

public class EmailMessageCallable /*extends AbstractCallableJob<EmailMessage, Void>*/ {

  protected final EmailSendService emailSendService;

  @Inject
  public EmailMessageCallable(EmailSendService emailSendService) {

    this.emailSendService = emailSendService;
  }

}
