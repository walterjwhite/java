package com.walterjwhite.encryption.modules.cli.handler;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.model.Encrypted;
import com.walterjwhite.encryption.service.FieldEncryptionService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import jakarta.inject.Inject;

/** Helper to list our client id. */
public class DigestHandler implements CommandLineHandler {
  protected final FieldEncryptionService fieldEncryptionService;

  @Inject
  public DigestHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      FieldEncryptionService fieldEncryptionService) {
    //    super(shutdownTimeoutInSeconds);
    this.fieldEncryptionService = fieldEncryptionService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    final Encrypted encrypted = new Encrypted(getInput(), EncryptionType.Digest);

    doDigest(encrypted);
    showResult(encrypted);
  }

  @Sensitive
  protected String getInput() throws IOException {
    return new BufferedReader(new InputStreamReader(System.in)).readLine();
  }

  protected void doDigest(Encrypted encrypted) throws NoSuchFieldException {
    fieldEncryptionService.encrypt(
        encrypted, Encrypted.class.getDeclaredField("password"), EncryptionType.Digest);
  }

  protected void showResult(Encrypted encrypted) {}
}
