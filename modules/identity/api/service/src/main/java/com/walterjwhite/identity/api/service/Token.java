package com.walterjwhite.identity.api.service;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.logging.annotation.Sensitive;
import java.time.Duration;
import java.time.LocalDateTime;
import lombok.*;

@ToString(doNotUseGetters = true)
@Data
public class Token {
  private static transient TokenService TOKEN_SERVICE;

  protected transient String key;
  protected Duration lifespan = Duration.ofSeconds(30);

  @Sensitive protected transient String value;

  protected transient LocalDateTime expirationDateTime;

  protected void init() {
    if (TOKEN_SERVICE == null) {
      TOKEN_SERVICE =
          ApplicationHelper.getApplicationInstance().getInjector().getInstance(TokenService.class);
    }
  }

  @Sensitive
  @SneakyThrows
  public String getValue() {
    if (value != null) {
      if (isExpired()) {
        return value;
      }

      value = null;
      expirationDateTime = null;
    }

    if (key == null) {
      throw new IllegalArgumentException("Key must not be null.");
    }

    init();
    value = TOKEN_SERVICE.get(key);
    expirationDateTime = LocalDateTime.now().plus(lifespan);

    return value;
  }

  protected boolean isExpired() {
    if (expirationDateTime == null) {
      return false;
    }

    return LocalDateTime.now().isBefore(expirationDateTime);
  }

  public void onSuccess(final String message, final Object... arguments) throws Exception {
    TOKEN_SERVICE.onSuccess(message, arguments);
  }

  public void onException(final Exception e) throws Exception {
    TOKEN_SERVICE.onException(e);
  }

  @Sensitive
  public void setValue(final String value) {
    this.value = value;
  }
}
