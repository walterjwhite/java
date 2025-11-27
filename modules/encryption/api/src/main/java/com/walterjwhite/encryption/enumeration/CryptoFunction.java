package com.walterjwhite.encryption.enumeration;

import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

public enum CryptoFunction {
  Encrypt {
    @Override
    public String decrypt(final String cipherText) throws IOException {
      final ByteArrayOutputStream plaintextStream = new ByteArrayOutputStream();
      ApplicationHelper.getApplicationInstance()
          .getInjector()
          .getInstance(EncryptionService.class)
          .decrypt(new ByteArrayInputStream(Base64.decodeBase64(cipherText)), plaintextStream);
      return plaintextStream.toString(Charset.defaultCharset());
    }

    @Override
    public String encrypt(final String plainText) throws IOException {
      final ByteArrayOutputStream cipherOutputStream = new ByteArrayOutputStream();

      ApplicationHelper.getApplicationInstance()
          .getInjector()
          .getInstance(EncryptionService.class)
          .encrypt(
              new ByteArrayInputStream(plainText.getBytes(Charset.defaultCharset())),
              cipherOutputStream);
      return Base64.encodeBase64String(cipherOutputStream.toByteArray());
    }
  },
  Digest {
    @Override
    public String decrypt(final String cipherText) {
      return cipherText;
    }

    @Override
    public String encrypt(String plainText) throws IOException {
      final DigestAlgorithm digestAlgorithm = null;
      return digestAlgorithm.compute(plainText);
    }
  };

  public abstract String decrypt(final String cipherText) throws IOException;

  public abstract String encrypt(final String plainText) throws IOException;
}
