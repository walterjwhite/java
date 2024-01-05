package com.walterjwhite.file.impl.service;

import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.service.DigestService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.api.service.FileStorageService;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import javax.transaction.Transactional;
import org.apache.commons.compress.utils.IOUtils;

public abstract class AbstractFileStorageService implements FileStorageService {
  protected final CompressionService compressionService;
  protected final EncryptionService encryptionService;
  protected final DigestService digestService;

  protected final boolean nop;
  protected final boolean debug;

  protected AbstractFileStorageService(
      CompressionService compressionService,
      EncryptionService encryptionService,
      DigestService digestService,
      boolean nop,
      boolean debug) {

    this.compressionService = compressionService;
    this.encryptionService = encryptionService;
    this.digestService = digestService;
    this.nop = nop;
    this.debug = debug;
  }

  @Transactional
  @Override
  public File put(File file) throws IOException, NoSuchAlgorithmException {
    final java.io.File sourceFile = new java.io.File(file.getSource());
    final String digest = digestService.compute(sourceFile);
    file.setChecksum(digest);

    return storeReference(file, sourceFile);
  }

  protected File storeReference(File file, final java.io.File sourceFile) throws IOException {
    try {
      // return repositoryProvider.get().query(new FindFileByChecksumQuery(file.getChecksum()));
      return file;
    } catch (RuntimeException e /*PersistenceException*/) {
      // repositoryProvider.get().create(file);

      final java.io.File replacedFile;
      if (!debug) {
        replacedFile = compressAndEncrypt(file, sourceFile);
      } else {
        replacedFile = null;
      }

      doPut(file);

      if (replacedFile != null) replacedFile.delete();

      return file;
    }
  }

  protected java.io.File compressAndEncrypt(File file, final java.io.File sourceFile)
      throws IOException {
    final java.io.File compressedAndEncryptedFile = java.io.File.createTempFile("encrypted", "xz");

    try (final InputStream inputStream = new FileInputStream(sourceFile)) {
      try (final OutputStream outputStream =
          compressionService.getCompressionStream(
              encryptionService.getEncryptionStream(
                  new FileOutputStream(compressedAndEncryptedFile)))) {
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        file.setSource(compressedAndEncryptedFile.getAbsolutePath());

        sourceFile.delete();

        return (compressedAndEncryptedFile);
      }
    }
  }

  protected abstract void doPut(File file) throws IOException;

  @Override
  public File put(java.io.File file) throws Exception {
    File wFile = new File(file.getAbsolutePath());
    return put(wFile);
  }

  @Override
  public void get(File file) throws Exception {
    doGet(file);

    final java.io.File sourceFile = new java.io.File(file.getSource());

    if (!debug) {
      final java.io.File decryptedAndDecompressedFile =
          java.io.File.createTempFile("decrypted", "xz");

      try (final InputStream inputStream =
          compressionService.getDecompressionStream(
              encryptionService.getDecryptionStream(new FileInputStream(sourceFile)))) {
        try (final OutputStream outputStream = new FileOutputStream(decryptedAndDecompressedFile)) {
          IOUtils.copy(inputStream, outputStream);

          outputStream.flush();
          file.setSource(decryptedAndDecompressedFile.getAbsolutePath());
          sourceFile.delete();
        }
      }
    }
  }

  protected abstract void doGet(File file) throws Exception;
}
