package com.walterjwhite.index.service;

import com.walterjwhite.datastore.api.model.entity.*;
import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.*;
import com.walterjwhite.serialization.api.service.SerializationService;
import java.io.ByteArrayOutputStream;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractIndexService<RequestType, ResponseType>
    implements IndexService<RequestType> {
  protected final IndexBridgeService indexBridgeService;
  protected final IndexNameService indexNameService;
  protected final SerializationService serializationService;

  @Override
  public IndexActivity<ResponseType> index(Entity entity) throws Exception {
    return doIndex(buildIndexableRecord(IndexAction.Index, entity));
  }

  protected abstract <ResponseType> ResponseType doIndex(final IndexableRecord indexableRecord)
      throws Exception;

  @Override
  public IndexActivity<ResponseType> update(Entity entity) throws Exception {
    return doUpdate(buildIndexableRecord(IndexAction.Update, entity));
  }

  protected abstract <ResponseType> ResponseType doUpdate(final IndexableRecord indexableRecord)
      throws Exception;

  @Override
  public IndexActivity<ResponseType> delete(Entity entity) throws Exception {
    return doDelete(buildIndexableRecord(IndexAction.Delete, entity));
  }

  protected abstract <ResponseType> ResponseType doDelete(final IndexableRecord indexableRecord)
      throws Exception;

  public IndexableRecord buildIndexableRecord(final IndexAction indexAction, final Entity entity)
      throws Exception {
    final Index index = getIndex(entity);

    final IndexableRecord indexableRecord = new IndexableRecord();
    indexableRecord.setIndex(index);
    indexableRecord.setEntityReference(
        new EntityReference(
            new EntityType(entity.getClass().getName(), EntityContainerType.Database),
            entity.getId()));

    indexableRecord.setObject(entity);

    // since this is no longer integrated with a database, this isn't set
    indexableRecord.getEntityReference().setId(1);

    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      serializationService.serialize(entity, baos);
      indexableRecord.setData(baos.toByteArray());
    }

    return indexableRecord;
  }

  protected Index getIndex(final Entity entity) {
    //    return new Index(indexNameService.getIndexName(entity.getClass()));
    return new Index(indexNameService.getIndexName(entity));
  }

  protected <ResponseType> IndexActivity<ResponseType> buildIndexActivity(
      final IndexAction indexAction,
      final IndexableRecord indexableRecord,
      final ResponseType response) {
    return (new IndexActivity(
        indexableRecord.getIndex(),
        indexableRecord.getEntityReference().getEntityType(),
        IndexAction.Index,
        indexableRecord.getEntityReference().getEntityId(),
        response));
  }
}
