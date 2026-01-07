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
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;

public class LocalFileStorageService extends AbstractFileStorageService {

  @Inject
  public LocalFileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestAlgorithm digestAlgorithm,
      @Property(NoOperation.class) boolean nop,
      @Property(Debug.class) boolean debug) {
    super(compressionService, encryptionService, digestAlgorithm, nop, debug);
  }

  @Transactional
  @Override
  protected void doPut(File file) throws IOException {

  }

  @Override
  protected void doGet(File file) throws IOException {
    final java.io.File outputFile = java.io.File.createTempFile(file.getChecksum(), "local");

    FileDBEntry fileDBEntry = null;
    FileUtils.writeByteArrayToFile(outputFile, fileDBEntry.getData());

    file.setSource(outputFile.getAbsolutePath());
  }

  @Transactional
  @Override
  public void delete(File file) {
    try {
      FileDBEntry fileDBEntry = null;
    } catch (Exception e) {
      throw new RuntimeException("Error deleting message", e);
    }
  }
}
