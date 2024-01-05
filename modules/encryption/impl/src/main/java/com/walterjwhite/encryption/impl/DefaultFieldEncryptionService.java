package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.EncryptionException;
import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.encryption.service.FieldEncryptionService;
import com.walterjwhite.encryption.service.SaltService;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import jakarta.inject.Inject;
import org.apache.commons.codec.binary.Base64;

public class DefaultFieldEncryptionService implements FieldEncryptionService {
  protected final EncryptionConfiguration encryptionConfiguration;

  protected final SaltService saltService;

  protected final EncryptionService encryptionService;

  @Inject
  public DefaultFieldEncryptionService(
      EncryptionConfiguration encryptionConfiguration,
      SaltService saltService,
      EncryptionService encryptionService) {

    this.encryptionConfiguration = encryptionConfiguration;
    this.saltService = saltService;
    this.encryptionService = encryptionService;
  }

  @Override
  public void encrypt(Object e, Field field, EncryptionType encryptionType) {
    try {
      final Field encryptedField =
          field.getDeclaringClass().getDeclaredField(FieldUtil.getEncryptedField(field));
      final String plaintext = (String) FieldUtil.getValue(e, field);
      if (plaintext == null) return;

      final String encryptedValue;

      if (EncryptionType.Digest.equals(encryptionType)) {
        final Field saltField =
            field.getDeclaringClass().getDeclaredField(FieldUtil.getSaltField(field));
        final byte[] salt = saltService.generate();
        final String saltEncoded = Base64.encodeBase64String(salt);
        FieldUtil.setValue(e, saltField, saltEncoded);

        encryptedValue = doDigest(plaintext, salt);
      } else {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encryptionService.encrypt(
            new ByteArrayInputStream(plaintext.getBytes(Charset.defaultCharset())), baos);
        encryptedValue = Base64.encodeBase64String(baos.toByteArray());
      }

      FieldUtil.setValue(e, encryptedField, encryptedValue);
    } catch (Exception exception) {
      throw new EncryptionException(exception);
    }
  }

  protected String doDigest(final String plaintext, final byte[] salt) {
    throw new UnsupportedOperationException("Un-implemented.");
  }

  public void decrypt(Object e, Field field, EncryptionType encryptionType) throws Exception {
    if (EncryptionType.Encrypt.equals(encryptionType)) {
      final Field encryptedField =
          field.getDeclaringClass().getDeclaredField(FieldUtil.getEncryptedField(field));

      final String value = (String) FieldUtil.getValue(e, encryptedField);
      if (value != null) {
        doDecrypt(e, field, value);
      }
    }
  }

  protected void doDecrypt(Object e, Field field, final String value)
      throws InvalidAlgorithmParameterException, InvalidKeyException, IOException,
          IllegalAccessException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    encryptionService.decrypt(new ByteArrayInputStream(Base64.decodeBase64(value)), baos);

    FieldUtil.setValue(e, field, baos.toString(Charset.defaultCharset()));
  }
}
