package com.walterjwhite.property.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

/** Helper to list our client id. */
@NoArgsConstructor(onConstructor_ = @Inject)
public class GetSecretPropertyHandler implements CommandLineHandler {
  //  @Inject
  //  public GetSecretPropertyHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(final String... arguments) {
    // change to assertion
    // we should not pass secrets on the command line, change this
    if (arguments == null || arguments.length % 2 != 0) {
      throw new IllegalArgumentException("Expected an even number of arguments");
    }

    //    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
  }
}
