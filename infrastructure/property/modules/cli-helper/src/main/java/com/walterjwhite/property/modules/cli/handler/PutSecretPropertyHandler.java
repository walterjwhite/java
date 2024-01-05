package com.walterjwhite.property.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.SecretService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import jakarta.inject.Inject;

/** Helper to list our client id. */
public class PutSecretPropertyHandler implements CommandLineHandler {
  protected final SecretService secretService;

  @Inject
  public PutSecretPropertyHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      SecretService secretService) {
    //    super(shutdownTimeoutInSeconds);
    this.secretService = secretService;
  }

  @Override
  public void run(final String... arguments) throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    for (final String secretKey : arguments) {
      secretService.put(secretKey, "new key", bufferedReader.readLine());
    }
  }
}
