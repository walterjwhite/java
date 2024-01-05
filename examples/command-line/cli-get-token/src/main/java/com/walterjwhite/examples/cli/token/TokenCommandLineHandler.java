package com.walterjwhite.examples.cli.token;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class TokenCommandLineHandler implements CommandLineHandler {
  protected final TokenService tokenService;

  @Inject
  public TokenCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      TokenService tokenService) {
    //    super(shutdownTimeoutInSeconds);
    this.tokenService = tokenService;
  }

  @Override
  public void run(String... arguments) {}
}
