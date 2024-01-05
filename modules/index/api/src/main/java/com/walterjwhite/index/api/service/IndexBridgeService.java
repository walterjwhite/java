package com.walterjwhite.index.api.service;

import java.io.Serializable;

// provides a mechanism to retrieve the original object from the instance indexed
public interface IndexBridgeService<
    RecordType extends Serializable, EntityType extends Serializable> {
  RecordType get(final EntityType entityType, final Integer id);
}
