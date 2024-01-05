package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.datastore.api.model.entity.EntityContainerType;
import com.walterjwhite.datastore.api.model.entity.EntityType;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.model.query.SearchQueryEntityType;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.api.service.SearchQueryService;
import com.walterjwhite.index.modules.elasticsearch.builder.ElasticSearchSearchQueryBuilder;
import java.io.IOException;
import java.util.List;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;

@Deprecated
public class ElasticSearchQuery implements SearchQueryService {
  protected final RestHighLevelClient restHighLevelClient;
  protected final SearchQuery searchQuery;
  protected final IndexNameService indexNameService;
  //  protected final Provider<Repository> repositoryProvider;

  public ElasticSearchQuery(
      RestHighLevelClient restHighLevelClient,
      SearchQuery searchQuery,
      IndexNameService indexNameService) {

    this.restHighLevelClient = restHighLevelClient;
    this.searchQuery = searchQuery;
    this.indexNameService = indexNameService;
    //    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public long getTotal() {
    return searchQuery.getTotal();
  }

  @Override
  public List<IndexRecord> getMatchedRecords() {
    return searchQuery.getIndexRecords();
  }

  @Override
  public void execute() throws IOException {
    final SearchRequest searchRequest = prepareSearch();
    final SearchResponse searchResponse =
        restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
  }

  protected SearchRequest prepareSearch() {
    final QueryBuilder queryBuilder =
        ElasticSearchSearchQueryBuilder.build(searchQuery.getPredicate());

    //    if (searchQuery.getResultSize() > 0) builder.setSize(searchQuery.getResultSize());
    //
    //    builder.setFrom(searchQuery.getResultOffset()).setExplain(true).get();
    //    searchQuery.setTotal(searchResponse.getHits().getTotalHits());
    // @TODO: store this as a duration for greater granularity
    //    searchQuery.setExecutionTime(searchResponse.getTook().millis());

    // search option
    // sort option
    // SearchAction searchAction = SearchAction.INSTANCE;
    //    SearchRequestBuilder builder = new SearchRequestBuilder(
    //        restHighLevelClient., searchAction);
    //
    //            .search(new SearchRequest(), RequestOptions.DEFAULT);

    final SearchRequest searchRequest = new SearchRequest(searchQuery.getIndex().getName());
    // searchRequest.source(queryBuilder);

    return searchRequest;
  }

  protected final String[] getEntityTypes(final List<SearchQueryEntityType> entityTypes) {
    final String[] entityTypeNames = new String[entityTypes.size()];

    int i = 0;
    for (SearchQueryEntityType entityType : entityTypes)
      entityTypeNames[i++] = entityType.getEntityType().getName();

    return (entityTypeNames);
  }

  protected void prepareResults(final SearchResponse searchResponse) {
    SearchHit[] results = searchResponse.getHits().getHits();
    for (SearchHit hit : results) {
      searchQuery
          .getIndexRecords()
          .add(
              new IndexRecord(
                  searchQuery, searchQuery.getIndex(), get(hit), hit.getId(), hit.getScore()));
    }
  }

  protected EntityType get(SearchHit hit) {
    //    repositoryProvider.get().query(new FindEntityTypeByNameQuery(hit.getType()) /*,
    //                          PersistenceOption.Create*/)

    return new EntityType(hit.getType(), EntityContainerType.Database);
  }
}
