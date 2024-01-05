package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.service.IndexNameService;
import com.walterjwhite.index.service.AbstractIndexMaintenanceService;
import java.io.IOException;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
//import org.elasticsearch.common.xcontent.XContentType;

@Singleton
public class ElasticSearchIndexMaintenanceService extends AbstractIndexMaintenanceService {
  protected final RestHighLevelClient restHighLevelClient;

  @Inject
  public ElasticSearchIndexMaintenanceService(
      final IndexNameService indexNameService, RestHighLevelClient restHighLevelClient) {
    super(indexNameService);
    this.restHighLevelClient = restHighLevelClient;
  }

  @Override
  protected void doCreate(final Index index) throws IOException {
    restHighLevelClient.indices().create(buildCreateRequest(index), RequestOptions.DEFAULT);
  }

  protected CreateIndexRequest buildCreateRequest(final Index index) {
    final CreateIndexRequest createIndexRequest = new CreateIndexRequest(index.getName());
    handleMapping(index, createIndexRequest);

    return createIndexRequest;
  }

  protected void handleMapping(final Index index, final CreateIndexRequest createIndexRequest) {
    if (index.getMapping() != null) {
      //createIndexRequest.mapping(index.getMapping(), XContentType.YAML);
      throw new UnsupportedOperationException("Migrate to Java Client");
    }
  }

  @Override
  protected void doDelete(Index index) throws IOException {
    restHighLevelClient
        .indices()
        .delete(new DeleteIndexRequest(index.getName()), RequestOptions.DEFAULT);
  }
}
