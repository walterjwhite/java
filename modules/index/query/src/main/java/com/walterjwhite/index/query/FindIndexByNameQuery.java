package com.walterjwhite.index.query;

import com.walterjwhite.datastore.query.AbstractSingularQuery;
import com.walterjwhite.index.api.model.index.Index;
import lombok.Getter;

@Getter
public class FindIndexByNameQuery extends AbstractSingularQuery<Index> {
  protected final String name;

  public static final String DEFAULT_INDEX_NAME = "default";

  public FindIndexByNameQuery(final String name) {
    super(Index.class, false);
    this.name = name;
  }
}
