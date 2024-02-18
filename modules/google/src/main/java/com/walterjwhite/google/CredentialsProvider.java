package com.walterjwhite.google;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.walterjwhite.google.property.GoogleCloudAccessToken;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CredentialsProvider implements Provider<ServiceAccountCredentials> {
  protected final ServiceAccountCredentials credentials;

  @Inject
  public CredentialsProvider(
      @Property(GoogleCloudAccessToken.class) String googleCloudAccessTokenFilename)
      throws IOException {
    credentials =
        ServiceAccountCredentials.fromStream(
            new BufferedInputStream(new FileInputStream(googleCloudAccessTokenFilename)));
  }

  @Override
  public ServiceAccountCredentials get() {
    return credentials;
  }
}
