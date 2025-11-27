package com.walterjwhite.index.api.service;

import java.io.IOException;

public interface IndexBulkService<RequestType> {
  void add(RequestType request) throws Exception;

  void flush() throws Exception;
}
