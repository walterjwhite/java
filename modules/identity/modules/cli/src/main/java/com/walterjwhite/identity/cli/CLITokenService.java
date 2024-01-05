package com.walterjwhite.identity.cli;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.timeout.TimeConstrainedMethodInvocation;
import com.walterjwhite.timeout.annotation.TimeConstrained;
import java.time.Duration;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CLITokenService implements TokenService, TimeConstrainedMethodInvocation {
  //  protected final TokenConfiguration tokenConfiguration;
  protected final PropertyManager propertyManager;

  @TimeConstrained
  public String get(final String helpText) {
    //    if(tokenConfiguration.isUseFirstArgument()){
    //
    //    }
    // this can be set via command-line, environment, etc.
    return propertyManager.get(Token.class);
  }

  // durationInMillis
  @Override
  public Duration getAllowedExecutionDuration() {
    return null;
  }

  public void onSuccess(final String message, final Object... arguments) {}

  public void onException(final Exception e) {}
}
