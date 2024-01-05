package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.encryption.enumeration.EncryptionAlgorithm;
import com.walterjwhite.encryption.enumeration.PaddingType;
import com.walterjwhite.encryption.enumeration.TransformationAlgorithm;
import com.walterjwhite.encryption.property.EncryptionKeyLength;
import com.walterjwhite.encryption.property.EncryptionSaltLength;
import com.walterjwhite.encryption.property.HashIterations;
import com.walterjwhite.encryption.property.InitializationVectorLength;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class EncryptionConfigurationProvider implements Provider<EncryptionConfiguration> {
  protected final EncryptionConfiguration encryptionConfiguration;

  @Inject
  public EncryptionConfigurationProvider(
      @Property(EncryptionAlgorithm.class) EncryptionAlgorithm encryptionAlgorithm,
      @Property(TransformationAlgorithm.class) TransformationAlgorithm transformationAlgorithm,
      @Property(PaddingType.class) PaddingType paddingType,
      @Property(DigestAlgorithm.class) DigestAlgorithm digestAlgorithm,
      @Property(EncryptionSaltLength.class) int saltLength,
      @Property(EncryptionKeyLength.class) int keyLength,
      @Property(HashIterations.class) int hashIterations,
      @Property(InitializationVectorLength.class) int ivLength) {
    encryptionConfiguration =
        new EncryptionConfiguration(
            encryptionAlgorithm,
            transformationAlgorithm,
            paddingType,
            digestAlgorithm,
            saltLength,
            keyLength,
            hashIterations,
            ivLength);
  }

  @Override
  public EncryptionConfiguration get() {
    return encryptionConfiguration;
  }
}
