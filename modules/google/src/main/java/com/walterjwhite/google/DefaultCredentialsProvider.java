package com.walterjwhite.google;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import jakarta.inject.Inject;

public class DefaultCredentialsProvider implements CredentialsProvider {
  protected final Credentials credentials;

  @Inject
  public DefaultCredentialsProvider(Credentials credentials) {

    this.credentials = credentials;
  }

  @Override
  public Credentials getCredentials() {
    return credentials;
  }
}
