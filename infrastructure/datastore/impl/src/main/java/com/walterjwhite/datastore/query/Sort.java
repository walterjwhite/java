package com.walterjwhite.datastore.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@RequiredArgsConstructor
public class Sort {
  protected final String fieldName;
  protected final SortOrder sortOrder;
}
