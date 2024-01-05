package com.walterjwhite.datastore.api.model.entity;

import java.io.Serializable;

public interface Entity<Type extends Serializable> extends Serializable {
  Type getId();
}
