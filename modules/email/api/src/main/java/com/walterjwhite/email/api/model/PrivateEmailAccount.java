package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractEntity;
import com.walterjwhite.encryption.model.Encrypted;
import java.time.Duration;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class PrivateEmailAccount extends AbstractEntity {


  protected EmailAccount emailAccount;

  @EqualsAndHashCode.Exclude protected EmailProvider provider;

  @EqualsAndHashCode.Exclude protected String username;

  @EqualsAndHashCode.Exclude protected Encrypted password;

  @EqualsAndHashCode.Exclude protected String domain;

  @EqualsAndHashCode.Exclude protected int folderType;

  @EqualsAndHashCode.Exclude protected Duration idleTimeout;

  public PrivateEmailAccount(final EmailAccount emailAccount) {
    this.emailAccount = emailAccount;
  }
}
