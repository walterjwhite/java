package com.walterjwhite.file.providers.local.service;

import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.enumeration.DigestAlgorithm;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.impl.service.AbstractFileStorageService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.enumeration.NoOperation;
import jakarta.inject.Inject;
import java.io.IOException;
import org.apache.commons.io.FileUtils;


public class LocalFileStorageService extends AbstractFileStorageService {
  protected final String localStoragePath;

  @Inject
  public LocalFileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestAlgorithm digestAlgorithm,
      @Property(NoOperation.class) boolean nop,
      @Property(Debug.class) boolean debug,
      @Property(LocalFileStoragePath.class) String localStoragePath) {
    super(compressionService, encryptionService, digestAlgorithm, nop, debug);
    this.localStoragePath = localStoragePath;
    setup();
  }

  protected void setup() {
    final java.io.File localStorage = new java.io.File(localStoragePath);
    if (!localStorage.exists()) {
      localStorage.mkdirs();
    }
  }

  @Override
  protected void doPut(File file) throws IOException {
    final java.io.File f = getFile(file);
    f.getParentFile().mkdirs();

    FileUtils.copyFile(new java.io.File(file.getSource()), getFile(file));
  }

  protected java.io.File getFile(File file) {
    return new java.io.File(
        localStoragePath
            + java.io.File.separator
            + String.join(
                java.io.File.separator, splitStringEvery(file.getChecksum(), SPLIT_INTERVAL)));
  }

  private static final int SPLIT_INTERVAL = 8;

  public String[] splitStringEvery(String s, int interval) {
    int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
    String[] result = new String[arrayLength];

    int j = 0;
    int lastIndex = result.length - 1;
    for (int i = 0; i < lastIndex; i++) {
      result[i] = s.substring(j, j + interval);
      j += interval;
    } 
    result[lastIndex] = s.substring(j);

    return result;
  }

  @Override
  protected void doGet(File file) throws IOException {
    final java.io.File outputFile = java.io.File.createTempFile(file.getChecksum(), "local");
    FileUtils.copyFile(getFile(file), outputFile);
    file.setSource(outputFile.getAbsolutePath());
  }

  @Override
  public void delete(File file) {
    try {
      getFile(file).delete();
    } catch (Exception e) {
      throw new RuntimeException("Error deleting message", e);
    }
  }
}
