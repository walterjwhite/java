package com.walterjwhite.examples.identity;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class IdentityExampleCommandLineHandler implements CommandLineHandler {
  protected final EntityCreator entityCreator;

  @Inject
  public IdentityExampleCommandLineHandler(
      EntityCreator entityCreator) {
    this.entityCreator = entityCreator;
  }

  @Override
  public void run(String... arguments) {
    entityCreator.createGuestAccount();
    entityCreator.createClientAccount();
  }
}
