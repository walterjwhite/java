package com.walterjwhite.file.providers.box.service;

import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.impl.service.AbstractFileStorageService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.enumeration.NoOperation;
import jakarta.inject.Inject;

public class BoxFileStorageService extends AbstractFileStorageService {
  @Inject
  public BoxFileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestService digestService,
      @Property(NoOperation.class) boolean nop,
      @Property(Debug.class) boolean debug) {
    super(compressionService, encryptionService, digestService, nop, debug);
  }

  @Override
  protected void doPut(File file) {}

  @Override
  protected void doGet(File file) {}

  @Override
  public void delete(File file) {}
}
