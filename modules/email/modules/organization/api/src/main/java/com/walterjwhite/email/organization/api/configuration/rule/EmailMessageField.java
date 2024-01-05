package com.walterjwhite.email.organization.api.configuration.rule;

import com.walterjwhite.email.api.model.Email;

public enum EmailMessageField {
  From {
    public Object get(Email email) {
      return email.getFrom().getEmailAccount().getEmailAddress();
    }
  },
  To {
    public Object get(Email email) {
      //      return email.getEmailEmailAccounts().stream()
      //          .filter(
      //              emailAddress ->
      // EmailRecipientType.To.equals(emailAddress.getEmailRecipientType()))
      //          .map(new EmailAddressMapFunction())
      //          .collect(Collectors.toList())
      //          .toArray();
      return null;
    }
  },
  Cc {
    public Object get(Email email) {
      //      return email.getEmailEmailAccounts().stream()
      //          .filter(
      //              emailAddress ->
      // EmailRecipientType.Cc.equals(emailAddress.getEmailRecipientType()))
      //          .map(new EmailAddressMapFunction())
      //          .collect(Collectors.toList())
      //          .toArray();
      return null;
    }
  },
  Bcc {
    public Object get(Email email) {
      //      return email.getEmailEmailAccounts().stream()
      //          .filter(
      //              emailAddress ->
      // EmailRecipientType.Bcc.equals(emailAddress.getEmailRecipientType()))
      //          .map(new EmailAddressMapFunction())
      //          .collect(Collectors.toList())
      //          .toArray();
      return null;
    }
  },
  Subject {
    public Object get(Email email) {
      return email.getSubject();
    }
  },
  Message {
    public Object get(Email email) {
      return email.getBody();
    }
  },
  Attachments {
    public Object get(Email email) {
      return email.getFiles();
    }
  };

  public abstract Object get(Email email);
}
