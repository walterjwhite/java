package com.walterjwhite.property.api;

public interface SecretService {
  void put(final String key, final String message, final String plaintext);

  String get(final String key);
}
