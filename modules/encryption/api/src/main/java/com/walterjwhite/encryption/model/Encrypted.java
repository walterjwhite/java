package com.walterjwhite.encryption.model;

import com.walterjwhite.encryption.enumeration.CryptoFunction;
import com.walterjwhite.logging.annotation.Sensitive;
import java.io.IOException;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PostLoad;
import javax.jdo.annotations.PreStore;
import lombok.*;

@NoArgsConstructor
@ToString(doNotUseGetters = true)
@PersistenceAware
public class Encrypted /*implements StoreCallback, LoadCallback*/ {
  @Sensitive
  @ToString.Exclude
  @NotPersistent
  @Getter(onMethod_ = @Sensitive)
  @Setter(onMethod_ = @Sensitive)
  protected transient String plainText;

  protected CryptoFunction cryptoFunction;

  @ToString.Exclude @EqualsAndHashCode.Exclude protected String cipherText;

  @Sensitive
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @Getter(onMethod_ = @Sensitive)
  @Setter(onMethod_ = @Sensitive)
  protected String salt;

  protected EncryptionPolicy applicableEncryptionPolicy;

  public Encrypted(@Sensitive final String plainText, CryptoFunction cryptoFunction) {
    this.plainText = plainText;
    this.cryptoFunction = cryptoFunction;
  }

  @PostLoad
  public void onPostLoad() throws IOException {
    plainText = cryptoFunction.decrypt(cipherText);
  }

  @PreStore
  public void onPreStore() throws IOException {
    cipherText = cryptoFunction.encrypt(plainText);
  }
}
