package com.walterjwhite.encryption.impl.digest;

import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.encryption.impl.EncryptionConfiguration;
import com.walterjwhite.encryption.service.DigestService;
import java.lang.reflect.InvocationTargetException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DigestMapping {
  SHA_1(DigestAlgorithm.SHA_1, SHA1DigestService.class),
  SHA_256(DigestAlgorithm.SHA_256, SHA256DigestService.class),
  SHA_512(DigestAlgorithm.SHA_512, SHA512DigestService.class);

  private final DigestAlgorithm digestAlgorithm;
  private final Class<? extends DigestService> digestServiceClass;

  public DigestService get(EncryptionConfiguration encryptionConfiguration) {
    try {
      return digestServiceClass
          .getConstructor(EncryptionConfiguration.class)
          .newInstance(encryptionConfiguration);
    } catch (InstantiationException
        | IllegalAccessException
        | InvocationTargetException
        | NoSuchMethodException e) {
      throw new RuntimeException("Application is mis-configured, unable to create digest service.");
    }
  }

  public static DigestMapping get(DigestAlgorithm digestAlgorithm) {
    // Arrays.stream(values()).findFirst(digestMapping -> digestMapping.)
    for (final DigestMapping digestMapping : values()) {
      if (digestMapping.getDigestAlgorithm().equals(digestAlgorithm)) return digestMapping;
    }

    throw new IllegalArgumentException("No provider found for algorithm:");
  }
}
