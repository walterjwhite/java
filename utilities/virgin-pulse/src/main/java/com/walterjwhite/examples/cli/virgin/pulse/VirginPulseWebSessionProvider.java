package com.walterjwhite.examples.cli.virgin.pulse;

import com.walterjwhite.browser.plugins.virgin.pulse.api.model.VirginPulseWebSession;
import com.walterjwhite.inject.cli.CLIApplication;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.FileInputStream;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class VirginPulseWebSessionProvider implements Provider<VirginPulseWebSession> {
  protected final SerializationService serializationService;

  @Override
  public VirginPulseWebSession get() {
    for (final String argument : CLIApplication.getRawArguments()) {
      try {
        return serializationService.deserialize(
            new FileInputStream(argument), VirginPulseWebSession.class);
      } catch (Exception e) {
        LOGGER.warn("unable to parse", e);
      }
    }

    throw new IllegalStateException("Unable to obtain VirginPulseWebSession");
  }
}
