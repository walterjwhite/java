package com.walterjwhite.email.modules.template.service;

import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.modules.template.model.EmailTemplate;
import com.walterjwhite.email.modules.template.model.EmailTemplateContact;
import com.walterjwhite.email.modules.template.model.EmailTemplateEmailRecipientProviderConfiguration;
import com.walterjwhite.email.modules.template.model.EmailTemplateSendRequest;
import com.walterjwhite.template.api.service.TemplateService;
import jakarta.inject.Inject;

import java.util.HashSet;
import java.util.Set;

public class DefaultEmailTemplateService implements EmailTemplateService {
  protected final TemplateService templateService;

  @Inject
  public DefaultEmailTemplateService(TemplateService templateService) {
    this.templateService = templateService;
  }

  @Override
  public Set<EmailTemplateSendRequest> render(
      EmailTemplate emailTemplate, Object... referenceDatas) {
    final Set<EmailTemplateSendRequest> emailTemplateSendRequests = new HashSet<>();

    for (Object referenceData : referenceDatas) {
      emailTemplateSendRequests.add(render(emailTemplate, referenceData));
    }

    return emailTemplateSendRequests;
  }

  
  protected EmailTemplateSendRequest render(
      EmailTemplate emailTemplate, Object referenceData) {
    final Email email = Email.builder()
            .subject(templateService.apply(emailTemplate.getSubjectTemplate(), referenceData))
            .body(templateService.apply(emailTemplate.getBodyTemplate(), referenceData)).build();

    

    addStaticRecipients(emailTemplate, email);
    updateRecipients(emailTemplate, email, referenceData);

    

    throw new UnsupportedOperationException("TODO: determine how to structure");



  }

















  protected void addStaticRecipients(EmailTemplate emailTemplate, Email email) {
    if (emailTemplate.getEmailTemplateContacts() != null
        && !emailTemplate.getEmailTemplateContacts().isEmpty()) {
      for (EmailTemplateContact emailTemplateContact : emailTemplate.getEmailTemplateContacts()) {







      }
    }
  }

  protected void updateRecipients(
      EmailTemplate emailTemplate, Email email, Object... referenceData) {

    for (EmailTemplateEmailRecipientProviderConfiguration
        emailTemplateEmailRecipientProviderConfiguration :
            emailTemplate.getEmailTemplateEmailRecipientProviderConfigurations()) {
      final Set<EmailAccount> contactsToAdd =
          getContacts(emailTemplateEmailRecipientProviderConfiguration, referenceData);

      if (contactsToAdd != null && !contactsToAdd.isEmpty()) {
        for (EmailAccount emailAccount : contactsToAdd) {








        }
      }
    }
  }

  
  protected Set<EmailAccount> getContacts(
      EmailTemplateEmailRecipientProviderConfiguration
          emailTemplateEmailRecipientProviderConfiguration,
      Object... referenceData) {










    return null;



  }
}
