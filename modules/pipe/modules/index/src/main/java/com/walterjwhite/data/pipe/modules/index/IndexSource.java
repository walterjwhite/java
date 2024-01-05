package com.walterjwhite.data.pipe.modules.index;

import com.walterjwhite.data.pipe.impl.AbstractSource;
import com.walterjwhite.index.api.model.index.IndexRecord;
import com.walterjwhite.index.api.model.query.SearchQuery;
import com.walterjwhite.index.api.service.IndexSearchService;
import java.util.Iterator;
import jakarta.inject.Inject;

// provides a stream of indexrecords
public class IndexSource extends AbstractSource<IndexRecord, IndexSourceConfiguration> {
  protected final IndexSearchService indexSearchService;
  protected final SearchQuery searchQuery;
  protected int index = 0;

  @Inject
  public IndexSource(IndexSearchService indexSearchService, SearchQuery searchQuery)
      throws Exception {
    this.indexSearchService = indexSearchService;
    this.searchQuery = searchQuery;
    indexSearchService.search(searchQuery);
  }

  public void close() {}

  /** TODO: For very large result sets, this would be terrible. */
  @Override
  public Iterator<IndexRecord> iterator() {
    return searchQuery.getIndexRecords().iterator();
  }

  @Override
  protected void doConfigure() {}
}
