package com.walterjwhite.index.api.service.query;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.index.api.model.index.Index;
import lombok.Getter;

@Getter
public class FindIndexByNameQuery extends AbstractQuery<Index, Index> {
  protected final String name;

  public FindIndexByNameQuery(String name) {
    super(0, 1, Index.class, Index.class, false);
    this.name = name;
  }
}
