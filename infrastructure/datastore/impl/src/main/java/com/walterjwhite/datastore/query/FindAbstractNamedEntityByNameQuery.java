package com.walterjwhite.datastore.query;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;

public class FindAbstractNamedEntityByNameQuery<EntityType extends AbstractNamedEntity>
    extends AbstractSingularQuery<EntityType> {
  protected final String name;

  public FindAbstractNamedEntityByNameQuery(final Class<EntityType> entityTypeClass, String name) {
    super(entityTypeClass, false);
    this.name = name;
  }
}
