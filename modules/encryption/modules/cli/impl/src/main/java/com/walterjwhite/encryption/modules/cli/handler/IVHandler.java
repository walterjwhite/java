package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.property.InitializationVectorLength;
import com.walterjwhite.encryption.service.SaltService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class IVHandler implements CommandLineHandler {
  protected final SaltService saltService;

  @Inject
  public IVHandler(
      SaltService saltService) {

    this.saltService = saltService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    for (final String argument : arguments) {
      doRunInstance(argument);
    }
  }

  protected void doRunInstance(final String argument) throws IOException {
    try (final FileOutputStream fos = new FileOutputStream(new File(argument))) {
      fos.write(saltService.generate(InitializationVectorLength.B_16));
    }
  }
}
