package com.walterjwhite.property.modules.cli.handler;

import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class GetSecretPropertyHandler implements CommandLineHandler {

  @Override
  public void run(final String... arguments) {
    if (arguments == null || arguments.length % 2 != 0) {
      throw new IllegalArgumentException("Expected an even number of arguments");
    }

  }
}
