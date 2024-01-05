package com.walterjwhite.index.modules.elasticsearch.impl;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestHighLevelClientBuilder;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

@Deprecated
public class RestHighLevelClientClientProvider implements Provider<RestHighLevelClient> {

  protected final RestClient restClient;

  @Inject
  public RestHighLevelClientClientProvider(final RestClient restClient) {
    this.restClient = restClient;
  }

  @Override
  public RestHighLevelClient get() {
    return new RestHighLevelClientBuilder(restClient)
            .setApiCompatibilityMode(true)
            .build();
  }
}
