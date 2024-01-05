package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.encryption.enumeration.EncryptionType;
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

  /** Only used for Exchange accounts. */
  @EqualsAndHashCode.Exclude protected String domain;

  /** Holds messages, folders, etc. */
  @EqualsAndHashCode.Exclude protected int folderType;

  @EqualsAndHashCode.Exclude protected Duration idleTimeout;


  public PrivateEmailAccount(
      EmailAccount emailAccount,
      EmailProvider provider,
      String username,
      String password,
      String domain) {
    this(emailAccount);
    this.provider = provider;
    this.username = username;
    this.password = new Encrypted(password, EncryptionType.Encrypt); // password;
    this.domain = domain;
  }

  public PrivateEmailAccount(
      final EmailAccount emailAccount, EmailProvider provider, String username, String password) {
    this(emailAccount);
    this.provider = provider;
    this.username = username;
    this.password = new Encrypted(password, EncryptionType.Encrypt); // password;
  }

  public PrivateEmailAccount(final EmailAccount emailAccount) {

    this.emailAccount = emailAccount;
  }
}
