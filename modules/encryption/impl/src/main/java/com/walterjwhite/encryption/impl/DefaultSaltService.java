package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.property.EncryptionSaltLength;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.property.api.annotation.Property;
import java.security.SecureRandom;
import jakarta.inject.Inject;

public class DefaultSaltService implements SaltService {
  protected final int saltLength;
  protected final SecureRandom secureRandom = new SecureRandom();

  @Inject
  public DefaultSaltService(@Property(EncryptionSaltLength.class) int saltLength) {
    this.saltLength = saltLength;
  }

  @Override
  public byte[] generate() {
    final byte[] random = new byte[saltLength];
    secureRandom.nextBytes(random);
    return (random);
  }

  @Override
  public byte[] generate(int length) {
    final byte[] random = new byte[length];
    secureRandom.nextBytes(random);
    return (random);
  }
}
