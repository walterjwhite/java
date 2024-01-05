package com.walterjwhite.index.modules.elasticsearch.impl;

import com.walterjwhite.index.api.property.RecordsPerBatch;
import com.walterjwhite.index.api.property.TimePerBatch;
import com.walterjwhite.index.api.service.IndexService;
import com.walterjwhite.index.service.AbstractIndexBulkService;
import com.walterjwhite.property.api.annotation.Property;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.IOException;
import jakarta.inject.Inject;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticIndexBulkService extends AbstractIndexBulkService<DocWriteRequest> {
  /** TODO: inject a JSON serialization service, YAML is also supported here. */
  protected final SerializationService serializationService;

  protected final RestHighLevelClient restHighLevelClient;

  @Inject
  public ElasticIndexBulkService(
      @Property(RecordsPerBatch.class) int recordsPerBatch,
      @Property(TimePerBatch.class) int timePerBatch,
      IndexService indexService,
      SerializationService serializationService,
      RestHighLevelClient restHighLevelClient) {
    super(recordsPerBatch, timePerBatch, indexService);

    this.serializationService = serializationService;
    this.restHighLevelClient = restHighLevelClient;
  }

  @Override
  protected void doFlush() throws IOException {
    final BulkRequest bulkRequest = new BulkRequest();

    for (DocWriteRequest docWriteRequest : get()) {
      bulkRequest.add(docWriteRequest);
    }

    restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
  }
}
