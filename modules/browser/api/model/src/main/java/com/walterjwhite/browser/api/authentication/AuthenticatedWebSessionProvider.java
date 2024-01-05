package com.walterjwhite.browser.api.authentication;

import com.walterjwhite.inject.cli.CLIApplication;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.FileInputStream;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class AuthenticatedWebSessionProvider implements Provider<AuthenticatedWebSession> {
  protected final SerializationService serializationService;

  @Override
  public AuthenticatedWebSession get() {
    for (final String argument : CLIApplication.getRawArguments()) {
      try {
        return serializationService.deserialize(
            new FileInputStream(argument), AuthenticatedWebSession.class);
      } catch (Exception e) {
        LOGGER.warn(String.format("Unable to deserialize %s", argument), e);
      }
    }

    throw new IllegalStateException("Unable to obtain WebSession");
  }
}
