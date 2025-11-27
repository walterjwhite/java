package com.walterjwhite.examples.cli;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  protected final SomeProvider blue;
  protected final SomeProvider red;
  protected final Set<SomeProvider> someProviders;

  @Inject
  public CLIExampleCommandLineHandler(
      @Blue SomeProvider blue, @Red SomeProvider red, final Set<SomeProvider> someProviders) {
    this.blue = blue;
    this.red = red;
    this.someProviders = someProviders;
  }

  @Override
  public void run(final String... arguments) {
    LOGGER.info("blue provider: {}", blue.get());
    LOGGER.info("red provider: {}", red.get());

    LOGGER.info("some providers: {}", someProviders);
  }
}
