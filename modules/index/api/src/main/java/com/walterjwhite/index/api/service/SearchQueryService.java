package com.walterjwhite.index.api.service;

import com.walterjwhite.index.api.model.index.IndexRecord;
import java.io.IOException;
import java.util.List;

public interface SearchQueryService {
  long getTotal();

  List<IndexRecord> getMatchedRecords();

  void execute() throws IOException;
}
