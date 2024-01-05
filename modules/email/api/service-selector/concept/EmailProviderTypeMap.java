package com.walterjwhite.email.api.service.selector.concept;

import com.walterjwhite.email.api.enumeration.EmailProviderType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailProvider;

@ProviderMapSupports(argumentType = Email.class, providerType = EmailProvider.class)
public class EmailProviderTypeMap implements ProviderTypeMap<Email, EmailProviderType> {
  public EmailProviderType getProviderType(final Email email) {
    return email.getFrom().getProvider().getType();
  }
}
