package com.walterjwhite.index.service;

import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.IndexSearchService;
import com.walterjwhite.index.api.service.SearchQueryService;
import javax.transaction.Transactional;

public abstract class AbstractIndexSearchService implements IndexSearchService {
  protected final IndexBridgeService indexBridgeService;
  protected final IndexNameService indexNameService;
  //  protected final Provider<Repository> repositoryProvider;

  public AbstractIndexSearchService(
      IndexBridgeService indexBridgeService, IndexNameService indexNameService) {

    this.indexBridgeService = indexBridgeService;
    this.indexNameService = indexNameService;
    //    this.repositoryProvider = repositoryProvider;
  }

  @Transactional
  @Override
  public void search(SearchQuery searchQuery) throws Exception {
    // require user to specify the index
    //    searchQuery.setIndex(indexNameService.getIndex(searchQuery.getEntityType()));

    final SearchQueryService searchQueryService = doSearch(searchQuery);
    searchQueryService.execute();
    searchQuery.setTotal(searchQueryService.getTotal());

    //    for (final IndexRecord indexRecord : searchQueryService.getMatchedRecords()) {
    //      searchQuery
    //          .getIndexRecords()
    //          .add(
    //              indexBridgeService.get(
    //                  indexRecord.getEntityType(),
    // indexNameService.getEntityId(indexRecord.getId())));
    //    }

    // update the search query with the response
    // repositoryProvider.get().merge(searchQuery);
    //    repositoryProvider.get().create(searchQuery);
  }

  protected abstract SearchQueryService doSearch(SearchQuery searchQuery);
}
