package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.service.EncryptionService;
import jakarta.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;

public class DefaultEncryptionService implements EncryptionService {

  protected final EncryptionConfiguration encryptionConfiguration;

  @Inject
  public DefaultEncryptionService(
      EncryptionConfiguration encryptionConfiguration) {

    this.encryptionConfiguration = encryptionConfiguration;
  }

  @Override
  public void encrypt(final InputStream plaintextStream, OutputStream outputStream)
      throws IOException {
    try (final OutputStream encryptedOutputStream = getEncryptionStream(outputStream)) {
      IOUtils.copy(plaintextStream, encryptedOutputStream);
      encryptedOutputStream.flush();
    }
  }

  @Override
  public OutputStream getEncryptionStream(OutputStream outputStream) {
    throw new UnsupportedOperationException("Encryption is deimplemented");
  }

  @Override
  public void decrypt(InputStream cipherStream, OutputStream plaintextStream) throws IOException {
    try (final InputStream decryptedOutputStream = getDecryptionStream(cipherStream)) {
      IOUtils.copy(decryptedOutputStream, plaintextStream);
      plaintextStream.flush();
    }
  }

  @Override
  public InputStream getDecryptionStream(InputStream cipherStream) {
    throw new UnsupportedOperationException("Encryption is deimplemented");
  }

  protected String getTransformation() {
    return (encryptionConfiguration.getEncryptionAlgorithm().name()
        + "/"
        + encryptionConfiguration.getTransformationAlgorithm().name()
        + "/"
        + encryptionConfiguration.getPaddingType().name());
  }
}
