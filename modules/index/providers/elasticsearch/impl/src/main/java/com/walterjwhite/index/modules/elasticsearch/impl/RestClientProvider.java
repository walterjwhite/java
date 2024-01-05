package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.modules.elasticsearch.property.ElasticSearchNodes;
import com.walterjwhite.property.api.annotation.Property;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class RestClientProvider implements Provider<RestClient> {

  protected final String elasticSearchNodes;

  @Inject
  public RestClientProvider(
          @Property(ElasticSearchNodes.class) String elasticSearchNodes) {
    this.elasticSearchNodes = elasticSearchNodes;
  }

  private static String[] getNodes(final String nodeString) {
    return nodeString.split(",");
  }

  private static HttpHost[] getHttpHosts(final String[] nodes) {
    final HttpHost[] httpHosts = new HttpHost[nodes.length];
    for (int i = 0; i < nodes.length; i++) {
      httpHosts[i] = HttpHost.create(nodes[i]);
    }

    return httpHosts;
  }

  @Override
  public RestClient get() {
    return RestClient.builder(getHttpHosts(getNodes(elasticSearchNodes))).build();
  }
}
