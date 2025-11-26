package com.walterjwhite.examples.cli.email.javamail.async;

import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.inject.cli.CLIApplication;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class EmailAccountProvider implements Provider<PrivateEmailAccount> {
  protected final SerializationService serializationService;

  @Override
  public PrivateEmailAccount get() {
    try {
      return serializationService.deserialize(
          new FileInputStream(CLIApplication.getRawArguments()[0]), PrivateEmailAccount.class);
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read PrivateEmailAccount");
    }
  }
}
