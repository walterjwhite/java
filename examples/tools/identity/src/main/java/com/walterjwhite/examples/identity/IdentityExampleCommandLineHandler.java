package com.walterjwhite.examples.identity;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;

public class IdentityExampleCommandLineHandler implements CommandLineHandler {
  protected final EntityCreator entityCreator;

  @Inject
  public IdentityExampleCommandLineHandler(
      //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds,
      EntityCreator entityCreator) {
    //    super(shutdownTimeoutInSeconds);
    this.entityCreator = entityCreator;
  }

  @Override
  public void run(String... arguments) {
    entityCreator.createGuestAccount();
    entityCreator.createClientAccount();
  }
}
