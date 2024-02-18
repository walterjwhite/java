package com.walterjwhite.datastore.api.model;

import java.io.Serializable;

public interface Entity<IDType extends Serializable> {
  IDType getId();
}
