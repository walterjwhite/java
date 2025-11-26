package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.enumeration.IndexAction;
import com.walterjwhite.index.api.model.index.IndexableRecord;

public interface IndexService<RequestType> {


  RequestType buildRequest(final IndexAction indexAction, final IndexableRecord indexableRecord);


}
