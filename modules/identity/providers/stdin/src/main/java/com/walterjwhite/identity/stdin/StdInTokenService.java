package com.walterjwhite.identity.stdin;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import jakarta.inject.Inject;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class StdInTokenService implements TokenService, TimeConstrainedMethodInvocation {
  protected final int timeoutInMilliseconds;

  @Inject
  public StdInTokenService(@Property(StdInTokenTimeout.class) final int timeoutInMilliseconds) {
    this.timeoutInMilliseconds = timeoutInMilliseconds;
  }

  @Sensitive
  @TimeConstrained
  public String get(final String text) {
    System.err.println(text);
    return new String(System.console().readPassword());
  }

  @Override
  public Duration getAllowedExecutionDuration() {
    return Duration.of(timeoutInMilliseconds, ChronoUnit.MILLIS);
  }

  @Override
  public void onSuccess(String message, Object... arguments) {
    System.out.println("Successfully processed token: " + message);
  }

  public void onException(final Exception e) {
    System.err.println("Error processing token: " + e.getMessage());
  }
}
