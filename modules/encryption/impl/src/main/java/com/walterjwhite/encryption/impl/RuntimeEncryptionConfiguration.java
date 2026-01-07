package com.walterjwhite.encryption.impl;

import com.walterjwhite.encryption.enumeration.EncryptionAlgorithm;
import com.walterjwhite.encryption.property.EncryptionKeyLength;
import com.walterjwhite.encryption.property.IVFilePath;
import com.walterjwhite.encryption.property.InitializationVectorLength;
import com.walterjwhite.encryption.property.KeyFilePath;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.Getter;
import org.apache.commons.io.IOUtils;

@Getter
@Singleton
public class RuntimeEncryptionConfiguration {
  protected final String keyFilePath;
  protected final String ivFilePath;
  protected byte[] keyData;
  protected byte[] ivData;
  protected final SaltService saltService;
  protected Key key;
  protected IvParameterSpec iv;

  @Inject
  public RuntimeEncryptionConfiguration(
      @Property(KeyFilePath.class) String keyFilePath,
      @Property(IVFilePath.class) String ivFilePath,
      SaltService saltService)
      throws IOException {
    this.keyFilePath = keyFilePath;
    this.ivFilePath = ivFilePath;
    this.saltService = saltService;
    setupKey();
  }

  protected void setupKey() throws IOException {
    if (new File(keyFilePath).exists()) {
      keyData = new byte[EncryptionKeyLength.L_256];
      ivData = new byte[InitializationVectorLength.B_16];
      IOUtils.read(new FileInputStream(keyFilePath), keyData);
      IOUtils.read(new FileInputStream(ivFilePath), ivData);
    } else {
      keyData = saltService.generate(EncryptionKeyLength.L_256);
      try (final FileOutputStream fos = new FileOutputStream(new File(keyFilePath))) {
        fos.write(keyData);
      }
      ivData = saltService.generate(InitializationVectorLength.B_16);
      try (final FileOutputStream fos = new FileOutputStream(new File(ivFilePath))) {
        fos.write(ivData);
      }
    }
    key = new SecretKeySpec(keyData, EncryptionAlgorithm.AES.getAlgorithmName());
    iv = new IvParameterSpec(ivData);
  }
}
