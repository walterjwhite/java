package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.service.EncryptionService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import jakarta.inject.Inject;
import java.io.*;
import java.nio.charset.Charset;


public class EncryptHandler implements CommandLineHandler {
  protected final EncryptionService encryptionService;

  @Inject
  public EncryptHandler(

      EncryptionService encryptionService) {

    this.encryptionService = encryptionService;
  }

  @Override
  public void run(final String... arguments) throws Exception {}

  @Sensitive
  protected String getInput() throws IOException {
    return new BufferedReader(new InputStreamReader(System.in)).readLine();
  }

  protected String doEncrypt() throws IOException {
    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    encryptionService.encrypt(
        new ByteArrayInputStream(getInput().getBytes(Charset.defaultCharset())), baos);

    return baos.toString();
  }
}
