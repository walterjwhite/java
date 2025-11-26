package com.walterjwhite.identity.cli;

import com.walterjwhite.identity.api.service.TokenService;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.PropertyManager;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class PropertyTokenService implements TokenService {
  protected final PropertyManager propertyManager;

  @Sensitive
  public String get(final String helpText) {
    final String value = propertyManager.get(Token.class);
    if (value == null) {
      throw new IllegalStateException("Expected token to be set, but it is not");
    }

    return value;
  }

  public void onSuccess(final String message, final Object... arguments) {}

  public void onException(final Exception e) {}
}
