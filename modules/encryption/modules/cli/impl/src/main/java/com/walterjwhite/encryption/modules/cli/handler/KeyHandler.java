package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.property.EncryptionKeyLength;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.io.*;

public class KeyHandler implements CommandLineHandler {
  protected final SaltService saltService;

  @Inject
  public KeyHandler(
      SaltService saltService) {

    this.saltService = saltService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    if (arguments.length == 0)
      throw new IllegalArgumentException("NO arguments were specified, specify at least 1.");

    for (final String argument : arguments) {
      try (final FileOutputStream fos = new FileOutputStream(new File(argument))) {
        fos.write(saltService.generate(EncryptionKeyLength.L_256));
      }
    }
  }
}
