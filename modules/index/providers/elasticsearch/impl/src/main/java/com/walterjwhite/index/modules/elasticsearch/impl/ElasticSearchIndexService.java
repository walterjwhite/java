package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexBridgeService;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.modules.elasticsearch.enumeration.ElasticSearchIndexAction;
import com.walterjwhite.index.service.AbstractIndexService;
import com.walterjwhite.serialization.api.service.SerializationService;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticSearchIndexService
    extends AbstractIndexService<DocWriteRequest, DocWriteResponse> {
  protected final Provider<RestHighLevelClient> restHighLevelClientProvider;

  @Inject
  public ElasticSearchIndexService(
      final IndexBridgeService indexBridgeService,
      final IndexNameService indexNameService,
      final SerializationService serializationService,
      final Provider<RestHighLevelClient> restHighLevelClientProvider) {
    super(indexBridgeService, indexNameService, serializationService);
    this.restHighLevelClientProvider = restHighLevelClientProvider;
  }

  @Override
  protected IndexResponse doIndex(final IndexableRecord indexableRecord) throws Exception {
    return restHighLevelClientProvider
        .get()
        .index(
            ElasticSearchIndexAction.Index.buildRequest(indexableRecord), RequestOptions.DEFAULT);
  }

  @Override
  protected UpdateResponse doUpdate(final IndexableRecord indexableRecord) throws Exception {
    return restHighLevelClientProvider
        .get()
        .update(
            ElasticSearchIndexAction.Update.buildRequest(indexableRecord), RequestOptions.DEFAULT);
  }

  @Override
  protected DeleteResponse doDelete(final IndexableRecord indexableRecord) throws Exception {
    return restHighLevelClientProvider
        .get()
        .delete(
            ElasticSearchIndexAction.Delete.buildRequest(indexableRecord), RequestOptions.DEFAULT);
  }

  public DocWriteRequest buildRequest(
      final IndexAction indexAction, final IndexableRecord indexableRecord) {
    return ElasticSearchIndexAction.get(indexAction).buildRequest(indexableRecord);
  }
}
