package com.walterjwhite.email.modules.template.service;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityReference;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.modules.template.model.EmailTemplate;
import com.walterjwhite.email.modules.template.model.EmailTemplateContact;
import com.walterjwhite.email.modules.template.model.EmailTemplateEmailRecipientProviderConfiguration;
import com.walterjwhite.email.modules.template.model.EmailTemplateSendRequest;
import com.walterjwhite.template.api.service.TemplateService;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.inject.Inject;

public class DefaultEmailTemplateService implements EmailTemplateService {
  protected final TemplateService templateService;

  @Inject
  public DefaultEmailTemplateService(TemplateService templateService) {
    this.templateService = templateService;
  }

  @Override
  public Set<EmailTemplateSendRequest> render(
      EmailTemplate emailTemplate, Serializable... referenceDatas) {
    final Set<EmailTemplateSendRequest> emailTemplateSendRequests = new HashSet<>();

    for (Serializable referenceData : referenceDatas) {
      emailTemplateSendRequests.add(render(emailTemplate, referenceData));
    }

    return emailTemplateSendRequests;
  }

  protected EmailTemplateSendRequest render(
      EmailTemplate emailTemplate, Serializable referenceData) {
    Email email = new Email();

    email.setSubject(templateService.apply(emailTemplate.getSubjectTemplate(), referenceData));
    email.setBody(templateService.apply(emailTemplate.getBodyTemplate(), referenceData));


    addStaticRecipients(emailTemplate, email);
    updateRecipients(emailTemplate, email, referenceData);

    // ...
    throw new UnsupportedOperationException("TODO: determine how to structure");
    //    return (EmailTemplateSendRequest)
    //        new EmailTemplateSendRequest(emailTemplate, getEntityReference(referenceData))
    //            .withEmail(email);
  }

  protected EntityReference getEntityReference(AbstractEntity referenceData) {
    // final Repository repository = repositoryProvider.get();

    //    repository.query(
    //            new FindEntityReferenceByTypeAndIdQuery(
    //                    repository.query(
    //                            new
    // FindEntityTypeByNameQuery(referenceData.getClass().getName())),
    //                    referenceData.getId()) /*,
    //                        PersistenceOption.Create*/);

    return new EntityReference(
        new EntityType(referenceData.getClass().getName(), EntityContainerType.Database),
        referenceData.getId());
  }

  protected void addStaticRecipients(EmailTemplate emailTemplate, Email email) {
    if (emailTemplate.getEmailTemplateContacts() != null
        && !emailTemplate.getEmailTemplateContacts().isEmpty()) {
      for (EmailTemplateContact emailTemplateContact : emailTemplate.getEmailTemplateContacts()) {
        //        email
        //            .getEmailEmailAccounts()
        //            .add(
        //                new EmailEmailAccount(
        //                    emailTemplateContact.getEmailAccount(),
        //                    email,
        //                    emailTemplateContact.getEmailRecipientType()));
      }
    }
  }

  protected void updateRecipients(
      EmailTemplate emailTemplate, Email email, Object... referenceData) {
    // get recipients (dynamic list)
    for (EmailTemplateEmailRecipientProviderConfiguration
        emailTemplateEmailRecipientProviderConfiguration :
            emailTemplate.getEmailTemplateEmailRecipientProviderConfigurations()) {
      final Set<EmailAccount> contactsToAdd =
          getContacts(emailTemplateEmailRecipientProviderConfiguration, referenceData);

      if (contactsToAdd != null && !contactsToAdd.isEmpty()) {
        for (EmailAccount emailAccount : contactsToAdd) {
          //          email
          //              .getEmailEmailAccounts()
          //              .add(
          //                  new EmailEmailAccount(
          //                      emailAccount,
          //                      email,
          //
          // emailTemplateEmailRecipientProviderConfiguration.getEmailRecipientType()));
        }
      }
    }
  }

  protected Set<EmailAccount> getContacts(
      EmailTemplateEmailRecipientProviderConfiguration
          emailTemplateEmailRecipientProviderConfiguration,
      Object... referenceData) {
    //    try {
    //      EmailRecipientProvider emailRecipientProvider =
    //          (EmailRecipientProvider)
    //              GuiceHelper.getGuiceApplicationInjector()
    //                  .getInstance(
    //                      Class.forName(
    //                          emailTemplateEmailRecipientProviderConfiguration
    //                              .getEmailRecipientProviderConfiguration()
    //                              .getName()));
    //      return emailRecipientProvider.getRecipients(referenceData);
    return null;
    //    } catch (ClassNotFoundException e) {
    //      throw new RuntimeException(e));
    //    }
  }
}
