package com.walterjwhite.file.modules.cli.handler;

import com.walterjwhite.encryption.api.service.CompressionService;
import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.file.api.model.File;
import com.walterjwhite.file.api.service.FileStorageService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;


public class DecryptHandler implements CommandLineHandler {
  protected final FileStorageService fileStorageService;
  protected final EncryptionService encryptionService;
  protected final CompressionService compressionService;

  @Inject
  public DecryptHandler(

      FileStorageService fileStorageService,
      EncryptionService encryptionService,
      CompressionService compressionService) {

    this.fileStorageService = fileStorageService;
    this.encryptionService = encryptionService;
    this.compressionService = compressionService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    validateInput(arguments);
    doRun(arguments);
  }

  protected void validateInput(final String[] arguments) {
    if (arguments == null || arguments.length == 0) {
      throw new IllegalArgumentException("Please specify at least 1 argument to encrypt");
    }
  }

  protected void doRun(final String[] arguments) throws Exception {
    for (final String argument : arguments) {
      doRunInstance(argument);
    }
  }

  protected void doRunInstance(final String argument) throws Exception {
    final File file = new File();
    file.setChecksum(argument);

    fileStorageService.get(file);
  }
}
