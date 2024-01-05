package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import com.walterjwhite.index.service.AbstractIndexSearchService;
import org.elasticsearch.client.RestHighLevelClient;

import jakarta.inject.Inject;

public class ElasticSearchIndexSearchService extends AbstractIndexSearchService {
  protected final RestHighLevelClient restHighLevelClient;

  @Inject
  public ElasticSearchIndexSearchService(
      IndexNameService indexNameService,
      IndexBridgeService indexBridgeService,
      RestHighLevelClient restHighLevelClient) {
    super(indexBridgeService, indexNameService);
    this.restHighLevelClient = restHighLevelClient;
  }

  @Override
  public SearchQueryService doSearch(SearchQuery searchQuery) {
    return (new ElasticSearchQuery(restHighLevelClient, searchQuery, indexNameService));
  }
}
