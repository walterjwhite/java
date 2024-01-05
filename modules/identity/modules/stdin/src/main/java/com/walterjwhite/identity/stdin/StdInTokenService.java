package com.walterjwhite.identity.stdin;

import com.walterjwhite.identity.api.service.TokenException;
import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

/** A user enters a token ... */
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class StdInTokenService implements TokenService, TimeConstrainedMethodInvocation {
  @TimeConstrained
  public String get(final String text) {
    final String helpText = "Please enter token for: " + text;

    try {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
      return bufferedReader.readLine();
    } catch (IOException e) {
      throw new TokenException(e);
    }
  }

  // durationInMillis
  @Override
  public Duration getAllowedExecutionDuration() {
    return null;
  }

  @Override
  public void onSuccess(String message, Object... arguments) {}

  public void onException(final Exception e) {}
}
