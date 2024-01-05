package com.walterjwhite.encryption.model;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.logging.annotation.Sensitive;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceAware;
import javax.jdo.annotations.PostLoad;
import javax.jdo.annotations.PreStore;
import lombok.*;
import org.apache.commons.codec.binary.Base64;

@NoArgsConstructor
@Data
@ToString(doNotUseGetters = true)
@PersistenceAware
public class Encrypted /*implements StoreCallback, LoadCallback*/ {
  @Getter(onMethod = @__(@Sensitive))
  @Setter(onMethod = @__(@Sensitive))
  @Sensitive
  @ToString.Exclude
  @NotPersistent
  protected transient String plainText;

  protected EncryptionType encryptionType;

  @ToString.Exclude @EqualsAndHashCode.Exclude protected String cipherText;

  @Getter(onMethod = @__(@Sensitive))
  @Setter(onMethod = @__(@Sensitive))
  @Sensitive
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  protected String salt;

  protected EncryptionPolicy applicableEncryptionPolicy;

  public Encrypted(String plainText, EncryptionType encryptionType) {
    this.plainText = plainText;
    this.encryptionType = encryptionType;
  }

  private static transient EncryptionService ENCRYPTION_SERVICE;
  private static transient DigestService DIGEST_SERVICE;

  private static synchronized void init() {
    if (DIGEST_SERVICE != null) {
      return;
    }

    DIGEST_SERVICE =
        ApplicationHelper.getApplicationInstance().getInjector().getInstance(DigestService.class);
    ENCRYPTION_SERVICE =
        ApplicationHelper.getApplicationInstance()
            .getInjector()
            .getInstance(EncryptionService.class);

    //    final SaltService saltService =
    //
    // ApplicationHelper.getApplicationInstance().getInjector().getInstance(SaltService.class);
  }

  protected void doEncrypt() throws IOException, NoSuchAlgorithmException {
    init();

    if (EncryptionType.Encrypt.equals(encryptionType)) {
      //      final EncryptionService encryptionService =
      //          ApplicationHelper.getApplicationInstance().getInjector()
      //              .getInstance(EncryptionServiceProvider.class)
      //              .get(applicableEncryptionPolicy);

      // cipherText =
      final ByteArrayOutputStream cipherOutputStream = new ByteArrayOutputStream();

      ENCRYPTION_SERVICE.encrypt(
          new ByteArrayInputStream(plainText.getBytes(Charset.defaultCharset())),
          cipherOutputStream);
      cipherText = Base64.encodeBase64String(cipherOutputStream.toByteArray());
    } else {
      //      final DigestService digestService =
      //          ApplicationHelper.getApplicationInstance().getInjector()
      //              .getInstance(DigestServiceProvider.class)
      //              .get(applicableEncryptionPolicy);

      cipherText =
          DIGEST_SERVICE.compute(
              new ByteArrayInputStream(plainText.getBytes(Charset.defaultCharset())));
      //        final byte[] salt = saltService.generate();
      // salt = Base64.encodeBase64String(salt);
    }
  }

  protected void doDecrypt()
      throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
    if (EncryptionType.Encrypt.equals(encryptionType)) {
      //      final EncryptionService encryptionService =
      //          ApplicationHelper.getApplicationInstance().getInjector()
      //              .getInstance(EncryptionServiceProvider.class)
      //              .get(applicableEncryptionPolicy);

      final ByteArrayOutputStream plaintextStream = new ByteArrayOutputStream();
      ENCRYPTION_SERVICE.decrypt(
          new ByteArrayInputStream(Base64.decodeBase64(cipherText)), plaintextStream);
      plainText = plaintextStream.toString(Charset.defaultCharset());
    }
  }

  // @Override
  @PostLoad
  public void onPostLoad()
      throws InvalidAlgorithmParameterException, IOException, InvalidKeyException {
    doDecrypt();
  }

  // @Override
  //  @PrePersist
  //  @PreUpdate
  @PreStore
  public void onPreStore() throws IOException, NoSuchAlgorithmException {
    doEncrypt();
  }
}
