package com.walterjwhite.property.api;

import com.walterjwhite.property.api.property.Secret;

public interface SecretService {
  void put(final String key, final String message, final String plaintext);

  String get(final String key);

  default String getAndSet(final Secret secret) {
    if (secret == null) {
      throw new IllegalArgumentException("Secret cannot be null.");
    }

    secret.setValue(get(secret.getKey()));
    return secret.getValue();
  }
}
