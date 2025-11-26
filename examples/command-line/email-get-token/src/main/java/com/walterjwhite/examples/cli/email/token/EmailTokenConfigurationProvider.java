package com.walterjwhite.examples.cli.email.token;

import com.walterjwhite.identity.email.EmailTokenConfiguration;
import com.walterjwhite.inject.cli.CLIApplication;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailTokenConfigurationProvider implements Provider<EmailTokenConfiguration> {
  protected final SerializationService serializationService;

  @Override
  public EmailTokenConfiguration get() {
    try {
      return serializationService.deserialize(
          new FileInputStream(CLIApplication.getRawArguments()[0]), EmailTokenConfiguration.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read EmailTokenConfiguration");
    }
  }
}
