package com.walterjwhite.file.providers.local.service;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.impl.service.AbstractFileStorageService;
import com.walterjwhite.file.impl.service.FindFileByChecksumQuery;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.property.api.enumeration.Debug;
import com.walterjwhite.property.api.enumeration.NoOperation;
import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import javax.transaction.Transactional;
import org.apache.commons.io.FileUtils;

public class LocalFileStorageService extends AbstractFileStorageService {
  protected final Provider<Repository> repositoryProvider;

  @Inject
  public LocalFileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestService digestService,
      Provider<Repository> repositoryProvider,
      @Property(NoOperation.class) boolean nop,
      @Property(Debug.class) boolean debug) {
    super(compressionService, encryptionService, digestService, nop, debug);
    this.repositoryProvider = repositoryProvider;
  }

  @Transactional
  @Override
  protected void doPut(File file) throws IOException {
    //    FileUtils.copyFile(new java.io.File(file.getSource()), getFile(file));

    // instead we need to stream it to the db
    repositoryProvider
        .get()
        .create(
            new FileDBEntry(
                file.getChecksum(),
                FileUtils.readFileToByteArray(new java.io.File(file.getSource()))));
  }

  @Override
  protected void doGet(File file) throws IOException {
    final java.io.File outputFile = java.io.File.createTempFile(file.getChecksum(), "local");

    FileDBEntry fileDBEntry =
        (FileDBEntry)
            repositoryProvider
                .get()
                .query(
                    new FindFileByChecksumQuery(file.getChecksum()) /*, PersistenceOption.Create*/);
    FileUtils.writeByteArrayToFile(outputFile, fileDBEntry.getData());

    file.setSource(outputFile.getAbsolutePath());
  }

  @Transactional
  @Override
  public void delete(File file) {
    try {
      //      getFile(file).delete();
      final Repository repository = repositoryProvider.get();
      FileDBEntry fileDBEntry =
          (FileDBEntry) repository.query(new FindFileByChecksumQuery(file.getChecksum()));
      repository.delete(fileDBEntry);
    } catch (Exception e) {
      throw new RuntimeException("Error deleting message", e);
    }
  }
}
