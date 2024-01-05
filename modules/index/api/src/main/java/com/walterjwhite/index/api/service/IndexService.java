package com.walterjwhite.index.api.service;

import com.walterjwhite.datastore.api.model.entity.Entity;
import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.IndexActivity;
import com.walterjwhite.index.api.model.index.IndexableRecord;

public interface IndexService<RequestType> {

  IndexActivity index(Entity entity) throws Exception;

  IndexActivity update(Entity entity) throws Exception;

  IndexActivity delete(Entity entity) throws Exception;

  IndexableRecord buildIndexableRecord(final IndexAction indexAction, final Entity entity)
      throws Exception;

  RequestType buildRequest(final IndexAction indexAction, final IndexableRecord indexableRecord);

  //  <ResponseType> ResponseType execute(IndexAction indexAction, final IndexableRecord
  // indexableRecord) throws Exception;
  //
  //  <RequestType> RequestType build(IndexAction indexAction, final IndexableRecord
  // indexableRecord);

  /** returns the Entity ID of the object we put in (JPA=>String, CSV=>Long)* */
  //  Serializable get(Serializable entityType, String indexId) throws Exception;
}
