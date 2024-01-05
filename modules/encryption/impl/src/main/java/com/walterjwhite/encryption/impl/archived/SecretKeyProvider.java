/*package com.walterjwhite.encryption.impl;

import com.walterjwhite.datastore.encryption.dto.EncryptionConfigurationDatastore;
import java.io.IOException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import sun.misc.BASE64Decoder;

public class SecretKeyProvider implements Provider<SecretKey> {
  protected final SecretKey secretKey;

  @Inject
  public SecretKeyProvider(EncryptionConfigurationDatastore encryptionConfiguration)
      throws IOException {
    final String secretPassword = encryptionConfiguration.getPassword();
    secretKey =
        new SecretKeySpec(
            new BASE64Decoder().decodeBuffer(secretPassword),
            encryptionConfiguration.getEncryptionAlgorithm().getAlgorithmName());
  }
  @Override
  public SecretKey get() {
    return (secretKey);
  }
}
*/
