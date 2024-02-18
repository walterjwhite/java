package com.walterjwhite.examples.cli.token;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class TokenCommandLineHandler implements CommandLineHandler {
  protected final TokenService tokenService;

  @Override
  public void run(String... arguments) throws Exception {
    final String token = tokenService.get("Help Text goes here");
    System.out.println("got: " + token);
  }
}
