package com.walterjwhite.email.api.model;


import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.file.api.model.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@Builder
public class Email extends AbstractEntity implements Comparable<Email>, Serializable {
  @ToString.Exclude protected PrivateEmailAccount from;

  @ToString.Exclude protected LocalDateTime createdDate;

  @ToString.Exclude protected LocalDateTime sentDateTime;

  protected String serverId;
  protected String messageId;

  @ToString.Exclude protected EmailConversation emailConversation;

  @ToString.Exclude protected Set<EmailEmailAccount> emailEmailAccounts = new HashSet<>();

  protected String subject;
  @ToString.Exclude protected String body;

  @ToString.Exclude protected Set<File> files = new HashSet<>();


  @ToString.Exclude protected EmailConversation conversation;

  @ToString.Exclude protected EmailSendRequest emailSendRequest;

  @Override
  public int compareTo(Email email) {
    return createdDate.compareTo(email.getCreatedDate());
  }
}
