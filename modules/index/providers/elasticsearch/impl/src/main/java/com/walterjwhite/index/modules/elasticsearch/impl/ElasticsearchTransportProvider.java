package com.walterjwhite.index.modules.elasticsearch.impl;

import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.elasticsearch.client.RestClient;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class ElasticsearchTransportProvider implements Provider<ElasticsearchTransport> {

  protected final RestClient restClient;

  @Inject
  public ElasticsearchTransportProvider(
          final RestClient restClient) {
    this.restClient = restClient;
  }

  @Override
  public ElasticsearchTransport get() {
    return new RestClientTransport(
            restClient,
            new JacksonJsonpMapper()
    );
  }
}
