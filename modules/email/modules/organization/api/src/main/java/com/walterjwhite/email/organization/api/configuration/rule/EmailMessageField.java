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







      return null;
    }
  },
  Cc {
    public Object get(Email email) {







      return null;
    }
  },
  Bcc {
    public Object get(Email email) {







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
