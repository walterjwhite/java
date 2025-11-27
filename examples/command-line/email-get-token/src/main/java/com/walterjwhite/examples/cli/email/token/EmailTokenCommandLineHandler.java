package com.walterjwhite.examples.cli.email.token;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class EmailTokenCommandLineHandler implements CommandLineHandler {
  protected final TokenService tokenService;

  @Inject
  public EmailTokenCommandLineHandler(
      TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @Override
  public void run(String... arguments) {}
}
