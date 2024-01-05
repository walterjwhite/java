package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.api.model.entity.Tag;
import com.walterjwhite.email.api.enumeration.EmailRecipientType;
import com.walterjwhite.file.api.model.File;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class Email extends AbstractEntity implements Comparable<Email> {
  @ToString.Exclude protected PrivateEmailAccount from;

  @ToString.Exclude protected LocalDateTime createdDate;

  @ToString.Exclude protected LocalDateTime sentDateTime;

  protected String serverId;
  // aka uuid
  protected String messageId;

  @ToString.Exclude protected EmailConversation emailConversation;

  @ToString.Exclude protected Set<EmailEmailAccount> emailEmailAccounts = new HashSet<>();

  protected String subject;
  @ToString.Exclude protected String body;

  @ToString.Exclude protected Set<File> files = new HashSet<>();

  @ToString.Exclude protected Set<Tag> tags = new HashSet<>();

  @ToString.Exclude protected EmailConversation conversation;

  @ToString.Exclude protected EmailSendRequest emailSendRequest;

  public Email(
      Set<EmailAccount> toRecipients, PrivateEmailAccount from, String subject, String body) {
    this();

    if (toRecipients != null && !toRecipients.isEmpty()) {
      for (EmailAccount emailAccount : toRecipients)
        emailEmailAccounts.add(new EmailEmailAccount(emailAccount, this, EmailRecipientType.To));
    }

    this.from = from;
    this.createdDate = LocalDateTime.now();
    this.subject = subject;
    this.body = body;
  }

  @Override
  public int compareTo(Email email) {
    return createdDate.compareTo(email.getCreatedDate());
  }
}
