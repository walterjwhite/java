package com.walterjwhite.email.api.service.selector.concept;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.service.EmailSendService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ServiceSelectorConsumer {
  protected final ServiceSelector selector;

  // this is basically a delegate pattern, still requires code ...
  public void send(final Email email) throws Exception {
    selector.getInstance(EmailSendService.class, EmailProviderTypeMap.class, email).send(email);
  }

  // NOTE: I cannot inject this unless I have the email I want to send ...
  protected final EmailSendService emailSendService;

  public void endState(final Email email) throws Exception {
    emailSendService.send(email);
  }
}
