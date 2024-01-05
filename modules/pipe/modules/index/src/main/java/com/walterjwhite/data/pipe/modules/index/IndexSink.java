package com.walterjwhite.data.pipe.modules.index;

import com.walterjwhite.data.pipe.impl.AbstractSink;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.index.api.service.IndexService;
import jakarta.inject.Inject;

/** Takes an entity / record and merely pushes it to an indexing service (such as elasticsearch). */
public class IndexSink
    extends AbstractSink</*IndexableRecord*/ AbstractEntity, IndexSinkConfiguration> {
  protected final IndexService indexService;

  @Inject
  public IndexSink(IndexService indexService) {

    this.indexService = indexService;
  }

  @Override
  public void close() {}

  @Override
  public void accept(/*IndexableRecord*/ AbstractEntity entity) {
    try {
      indexService.index(/*(AbstractEntity)indexableRecord.getObject()*/ entity);
    } catch (Exception e) {
      throw new RuntimeException("Error indexing data", e);
    }
  }

  @Override
  protected void doConfigure() {}
}
