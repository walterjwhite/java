package com.walterjwhite.identity.api.service;

public interface TokenService {
  String get(final String helpText) throws InterruptedException;

  default void onSuccess(final String message, final Object... arguments) throws Exception {}

  default void onException(final Exception e) throws Exception {}
}
