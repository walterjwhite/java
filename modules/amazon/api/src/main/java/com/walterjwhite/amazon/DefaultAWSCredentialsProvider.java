package com.walterjwhite.amazon;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class DefaultAWSCredentialsProvider implements AWSCredentialsProvider {
  protected final DefaultAWSCredentials awsCredentials;

  public DefaultAWSCredentialsProvider(String accessKeyId, String secretAccessKey) {

    awsCredentials = new DefaultAWSCredentials(accessKeyId, secretAccessKey);
  }

  @Override
  public com.amazonaws.auth.AWSCredentials getCredentials() {
    return awsCredentials;
  }

  @Override
  public void refresh() {}

  public class DefaultAWSCredentials implements AWSCredentials {
    protected final String accessKeyId;
    protected final String secretAccessKey;

    public DefaultAWSCredentials(String accessKeyId, String secretAccessKey) {

      this.accessKeyId = accessKeyId;
      this.secretAccessKey = secretAccessKey;
    }

    @Override
    public String getAWSAccessKeyId() {
      return accessKeyId;
    }

    @Override
    public String getAWSSecretKey() {
      return secretAccessKey;
    }
  }
}
