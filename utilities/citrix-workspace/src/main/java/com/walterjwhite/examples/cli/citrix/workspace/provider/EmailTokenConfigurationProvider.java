package com.walterjwhite.examples.cli.citrix.workspace.provider;

import com.walterjwhite.identity.email.EmailTokenConfiguration;
import com.walterjwhite.inject.cli.CLIApplication;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.FileInputStream;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailTokenConfigurationProvider implements Provider<EmailTokenConfiguration> {
  protected final SerializationService serializationService;

  @Override
  public EmailTokenConfiguration get() {
    for (final String argument : CLIApplication.getRawArguments()) {
      try {
        return serializationService.deserialize(
            new FileInputStream(argument), EmailTokenConfiguration.class);
      } catch (Exception e) {
        LOGGER.warn(String.format("Unable to deserialize %s", argument), e);
      }
    }

    throw new IllegalStateException("Unable to obtain EmailTokenConfiguration");
  }
}
