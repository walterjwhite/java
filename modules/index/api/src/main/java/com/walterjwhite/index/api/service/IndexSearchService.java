package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.model.query.SearchQuery;

public interface IndexSearchService {
  // Add ability to serialize criteria API to database (to re-run query again and again).
  void search(SearchQuery searchQuery) throws Exception;
}
