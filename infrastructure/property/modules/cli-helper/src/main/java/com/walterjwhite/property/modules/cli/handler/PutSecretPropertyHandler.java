package com.walterjwhite.property.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.SecretService;
import jakarta.inject.Inject;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PutSecretPropertyHandler implements CommandLineHandler {
  protected final SecretService secretService;

  @Inject
  public PutSecretPropertyHandler(
      SecretService secretService) {
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
