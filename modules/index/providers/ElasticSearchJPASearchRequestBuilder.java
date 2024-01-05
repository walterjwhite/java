package com.walterjwhite.google.guice.elasticsearch.api;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity_;
import com.walterjwhite.google.guice.elasticsearch.impl.AbstractElasticSearchAware;
import java.util.HashSet;
import java.util.Set;
import jakarta.inject.Inject;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;

public class ElasticSearchJPASearchRequestBuilder extends AbstractElasticSearchAware {
  protected transient SearchRequestBuilder searchRequestBuilder;

  protected Set<Class<? extends AbstractEntity_>> searchEntityTypes;

  @Inject
  public ElasticSearchJPASearchRequestBuilder(RestHighLevelClient restHighLevelClient) {
    super(restHighLevelClient);
    searchEntityTypes = new HashSet<>();
  }

  public ElasticSearchJPASearchRequestBuilder searchTypes(
      Class<? extends AbstractEntity_>... entityClasses) {
    if (searchRequestBuilder != null)
      throw new IllegalStateException(
          "SearchRequestBuilder was already initialized, check your code, only initialize it"
              + " once.");

    for (Class<? extends AbstractEntity_> entityClass : entityClasses)
      searchEntityTypes.add(entityClass);

    searchRequestBuilder = restHighLevelClient.prepareSearch(getIndexes(entityClasses));
    searchRequestBuilder.setTypes(getTypes(entityClasses));
    return (this);
  }

  //    public ElasticSearchJPASearchRequestBuilder setStart(int start){
  //        searchRequestBuilder.setFrom(start);
  //        return(this);
  //    }
  //
  //    public ElasticSearchJPASearchRequestBuilder setSize(int size){
  //        searchRequestBuilder.setSize(size);
  //        return(this);
  //    }

  public SearchRequestBuilder getSearchRequestBuilder() {
    return (searchRequestBuilder);
  }

  public ElasticSearchJPASearchRequestBuilder searchFields(
      Object value, final Attribute attribute) {
    if (attribute == null) {
      // iterate through types and add all of them
      throw new UnsupportedOperationException("how to search multiple fields?");
    } else {
      // if this is a single attribute, no problem but how do we do multiple?
      searchRequestBuilder.setQuery(QueryBuilders.fuzzyQuery(attribute.getName(), value));
    }

    return (this);
  }

  public SearchResponse doSearch() {
    if (searchRequestBuilder == null)
      throw new IllegalStateException(
          "SearchRequestBuilder was not initialized, please specify an index(es)");

    // set some defaults
    // timeout
    // start
    // size of resultset

    return (searchRequestBuilder.get());
  }
}
