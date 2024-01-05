package com.walterjwhite.datastore.query.tag;

import com.walterjwhite.datastore.api.model.entity.Tag;
import com.walterjwhite.datastore.query.AbstractSingularQuery;

public class FindTagByNameQuery extends AbstractSingularQuery<Tag> {
  protected final String name;

  public FindTagByNameQuery(String name) {
    super(Tag.class, false);
    this.name = name;
  }
}
