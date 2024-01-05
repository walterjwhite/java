package com.walterjwhite.index.api.service;

import java.io.IOException;

public interface IndexBulkService<RequestType> {
  /**
   * Adds the request to the batch and automatically flush the batch if the criteria is met to
   * flush.
   *
   * @param request
   * @throws IOException
   */
  void add(RequestType request) throws Exception;

  /**
   * Manually flush the batch operation
   *
   * @throws IOException
   */
  void flush() throws Exception;
}
